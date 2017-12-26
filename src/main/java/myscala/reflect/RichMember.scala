/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.reflect

import java.lang.reflect.Member

import myscala.Validate

private[reflect] class RichMember(member: Member) {

    Validate.notNull(member)

    def isAccessible: Boolean = ReflectionSupport.isAccessible(member)

}
