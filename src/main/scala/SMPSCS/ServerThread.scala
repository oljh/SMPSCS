package SMPSCS

import java.io._
import java.net.{Socket, SocketException}

class ServerThread(socket: Socket) extends Thread("ServerThread") {

  override def run(): Unit = {
    try {
      val out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream))
      val in = new ObjectInputStream(new DataInputStream(socket.getInputStream()));

      val data= Array("One", "Two","Three")
      out.writeObject(data)
      out.close();
      in.close();
      socket.close()
    }
    catch {
      case e: SocketException =>
        () // avoid stack trace when stopping a client with Ctrl-C
      case e: IOException =>
        e.printStackTrace();
    }
  }

}
