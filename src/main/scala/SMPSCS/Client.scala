package SMPSCS

import java.io.{DataInputStream, DataOutputStream, IOException, ObjectOutputStream}
import java.net.{InetAddress, Socket}


object Сlient {

  def main(args: Array[String]) {
    val filter: Int => Boolean = try {
      Integer.parseInt(args(0)) match {
        case 1 => x: Int => x % 2 != 0
        case 2 => x: Int => x % 2 == 0
        case _ => x: Int => x != 0
      }
    }
    catch {
      case _ => x: Int => x < 100
    }

    try {
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, 9999)
      val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))
      val in = new DataInputStream(socket.getInputStream())

      out.writeObject(filter)
      out.flush()

      while (true) {
        val x = in.readInt()
        println("x = " + x)
      }
      out.close()
      in.close()
      socket.close()
    }
    catch {
      case e: IOException =>
        e.printStackTrace()
    }
  }

}