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

import scala.util.{Success, Try}

object AnnotationSearching {

    private val objClz = classOf[java.lang.Object]

    def search[A <: Annotation](clz: Class[_], annoType: Class[A]): Option[A] = {
        if (clz == null || annoType == null) return None

        var anno = clz.getAnnotation(annoType)
        if (anno != null) {
            return Some(anno)
        }

        clz.getAllSuperClasses(_ != objClz).foreach { c =>
            anno = c.getAnnotation(annoType)
            if (anno != null) {
                return Some(anno)
            }
        }

        clz.getAllInterfaces({ _ => true }).foreach { c =>
            anno = c.getAnnotation(annoType)
            if (anno != null) {
                return Some(anno)
            }
        }

        None
    }

    def search[A <: Annotation](method: Method, annoType: Class[A]): Option[A] = {
        if (method == null || annoType == null) return None

        var anno = method.getAnnotation(annoType)
        if (anno != null) {
            return Some(anno)
        }

        val clz: Class[_] = method.getDeclaringClass

        clz.getAllSuperClasses(_ != objClz).foreach { c =>

            Try(c.getMethod(method.getName, method.getParameterTypes: _*)) match {
                case Success(x) =>
                    anno = x.getAnnotation(annoType)
                    if (anno != null) {
                        return Some(anno)
                    }
                case _ =>
            }
        }

        clz.getAllInterfaces(_ => true).foreach { c =>

            Try(c.getMethod(method.getName, method.getParameterTypes: _*)) match {
                case Success(x) =>
                    anno = x.getAnnotation(annoType)
                    if (anno != null) {
                        return Some(anno)
                    }
                case _ =>
            }
        }

        search(clz, annoType)
    }

    def search[A <: Annotation](field: Field, annoType: Class[A]): Option[A] = {
        if (field == null || annoType == null) return None

        var anno = field.getAnnotation(annoType)
        if (anno != null) {
            return Some(anno)
        }

        val clz: Class[_] = field.getDeclaringClass

        clz.getAllSuperClasses(_ != objClz).foreach { c =>

            Try(c.getField(field.getName)) match {
                case Success(x) =>
                    anno = x.getAnnotation(annoType)
                    if (anno != null) {
                        return Some(anno)
                    }
                case _ =>
            }
        }

        search(clz, annoType)
    }

    def search[A <: Annotation](constructor: Constructor[_], annoType: Class[A]): Option[A] = {
        if (constructor == null || annoType == null) return None

        val anno = constructor.getAnnotation(annoType)
        if (anno != null) return Some(anno)
        
        search(constructor.getDeclaringClass, annoType)
    }
}
