/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import java.lang.reflect.{Constructor, Field, Member, Method}

import scala.language.implicitConversions

package object reflect {

    implicit def memberToRichMember(member: Member): RichMember = new RichMember(member)

    implicit def classToRichClass(clz: Class[_]): RichClass = new RichClass(clz)

    implicit def classToRichReflection(clz: Class[_]): RichReflect = new RichReflect(clz)

    implicit def methodToRichMethod(method: Method): RichMethod = new RichMethod(method)

    implicit def fieldToRichField(field: Field): RichField = new RichField(field)

    implicit def constructorToRichConstructor[T <: scala.AnyRef](constructor: Constructor[T]): RichConstructor[T] = new RichConstructor(constructor)

}
