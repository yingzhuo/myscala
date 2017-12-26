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
import java.lang.reflect.Field

import myscala.Validate

private[reflect] class RichField(val field: Field) {

    Validate.notNull(field)

    def hashAnnotation[A <: Annotation](annotationType: Class[A]): Boolean = field.getAnnotation(annotationType) != null

    def getAnnotationOption[A <: Annotation](annotationType: Class[A]): Option[A] = Option(field.getAnnotation(annotationType))

}
