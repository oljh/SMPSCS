package SMPSCS

import java.io._
import java.net.{InetAddress, Socket}


object Сlient {
  private val PORT: Int = 5678;

  def main(args: Array[String]) {

    try {
      val ia = InetAddress.getByName("localhost")
      val socket = new Socket(ia, PORT)
      val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()))
      val in = new ObjectInputStream(new DataInputStream(socket.getInputStream()))


      // out.writeObject(filter)
      // out.flush()
      val arr: Array[Any] = in.readObject().asInstanceOf[Array[Any]]
      arr.foreach(f => println(f))

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