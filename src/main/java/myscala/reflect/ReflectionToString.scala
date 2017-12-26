/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.reflect

trait ReflectionToString {

    override def toString: String = s"${getClass.getName}(${
        getClass.getDeclaredFields.map(f => {
            f.setAccessible(true)
            s"${f.getName}=${f.get(this)}"
        }).mkString(",")
    })"

}
