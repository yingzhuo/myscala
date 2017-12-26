/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.reflect

import java.lang.annotation.Annotation
import java.lang.reflect.{Constructor, Field, Method}

import scala.collection.mutable
import scala.language.existentials
import scala.util.Try
import myscala.Validate

private[reflect] class RichClass(val cls: Class[_]) {

    Validate.notNull(cls)

    def isInner: Boolean = cls.getEnclosingClass != null

    def getPackageName: String = {
        var cn: String = cls.getName
        if (cn.isEmpty) return ""
        while (cn.charAt(0) == '[') {
            cn = cn.substring(1)
        }
        if (cn.charAt(0) == 'L' && cn.charAt(cn.length - 1) == ';') cn = cn.substring(1)
        val i = cn.lastIndexOf('.')
        if (i == -1) return ""
        cn.substring(0, i)
    }

    def getAllSuperClasses(filter: Class[_] => Boolean = { _ => true }): List[Class[_]] = {
        var c = cls: Class[_]
        Stream.continually {
            c = c.getSuperclass
            c
        }.takeWhile(_ != null).toList.filter(filter)
    }

    def getAllInterfaces(filter: Class[_] => Boolean = { _ => true }): List[Class[_]] = {

        // Inner Method
        def doGetAllInterfaces(cls: Class[_], set: mutable.Set[Class[_]]): Unit = {
            cls.getInterfaces.foreach { i =>
                set += i
                doGetAllInterfaces(i, set)
            }
        }

        val set = mutable.Set[Class[_]]()
        doGetAllInterfaces(cls, set)

        cls.getAllSuperClasses().foreach { s =>
            doGetAllInterfaces(s, set)
        }

        set.toList.filter(filter)
    }

    def hasAnnotation[A <: Annotation](annotationType: Class[A]): Boolean = cls.getAnnotation(annotationType) != null

    def getAnnotationOption[A <: Annotation](annotationType: Class[A]): Option[A] = Option(cls.getAnnotation(annotationType))

    def getAccessibleConstructor(parameterTypes: Class[_]*): Constructor[_] = getAccessibleConstructorOption(parameterTypes: _*) match {
        case None => throw new NoSuchMethodException()
        case Some(x) => x
    }

    def getAccessibleConstructorOption(parameterTypes: Class[_]*): Option[Constructor[_]] = {
        val ctor = Try(cls.getConstructor(parameterTypes: _*))

        if (ctor.isFailure) return None

        ctor.get match {
            case c if !ReflectionSupport.isAccessible(c) => None
            case c if !ReflectionSupport.isAccessible(c.getDeclaringClass) => None
            case c => Option(c)
        }
    }

    def getAccessibleMethod(methodName: String, parameterTypes: Class[_]*): Method = getAccessibleMethodOption(methodName, parameterTypes: _*) match {
        case None => throw new NoSuchMethodException()
        case Some(x) => x
    }

    def getAccessibleMethodOption(methodName: String, parameterTypes: Class[_]*): Option[Method] = {
        val method = Try(cls.getMethod(methodName, parameterTypes: _*))

        if (method.isFailure) return None

        method.get match {
            case m if !ReflectionSupport.isAccessible(m) => None
            case m if !ReflectionSupport.isAccessible(m.getDeclaringClass) => None
            case m => Option(m)
        }
    }

    def getAccessibleField(fieldName: String): Field = getAccessibleFieldOption(fieldName) match {
        case None => throw new NoSuchFieldException()
        case Some(x) => x
    }

    def getAccessibleFieldOption(fieldName: String): Option[Field] = {

        val field = Try(cls.getField(fieldName))

        if (field.isFailure) return None

        field.get match {
            case f if !ReflectionSupport.isAccessible(f) => None
            case f if !ReflectionSupport.isAccessible(f.getDeclaringClass) => None
            case f => Option(f)
        }
    }
}
