/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.reflect

import java.lang.reflect.{Member, Modifier}

protected object ReflectionSupport {

    def isAccessible(m: Member): Boolean = m != null && Modifier.isPublic(m.getModifiers) && !m.isSynthetic

    def isAccessible(c: Class[_]): Boolean = {
        var cls = c
        while (cls != null) {
            if (!Modifier.isPublic(cls.getModifiers)) return false
            cls = cls.getEnclosingClass
        }
        true
    }

    def nullToEmpty(array: Array[Any]): Array[Any] = array match {
        case null => Array[Any]()
        case _ => array
    }

}
