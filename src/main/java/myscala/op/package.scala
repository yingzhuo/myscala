/*                              _
  _ __ ___  _   _ ___  ___ __ _| | __ _
 | '_ ` _ \| | | / __|/ __/ _` | |/ _` |
 | | | | | | |_| \__ \ (_| (_| | | (_| |
 |_| |_| |_|\__, |___/\___\__,_|_|\__,_|
            |___/

 https://github.com/yingzhuo/myscala
*/
package myscala

import scala.language.implicitConversions

/* 向Scalaz致敬 */

package object op {

    implicit def xToOptionableOps[X: Optionable](x: X): OptionableOps[X] = new OptionableOps[X](x)

    implicit def xToOrderingOps[X: Ordering](x: X): OrderingOps[X] = new OrderingOps[X](x)

    implicit def xToShowOps[X: Show](x: X): ShowOps[X] = new ShowOps[X](x)

    implicit def xToEnumOps[X: Enum](x: X): EnumOps[X] = new EnumOps[X](x)

}
