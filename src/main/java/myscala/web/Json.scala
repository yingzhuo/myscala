/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala.web

object Json {

    def apply(code: String, pairs: (String, scala.Any)*): Json = {
        apply(code, List(), Map(pairs: _*))
    }

}

final case class Json(code: String, errorMessages: List[String] = List(), payload: Map[String, scala.Any] = Map())
