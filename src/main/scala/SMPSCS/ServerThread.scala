package SMPSCS

import java.io.{DataInputStream, DataOutputStream, IOException, ObjectInputStream}
import java.net.{Socket, SocketException}
import java.util.Random

case class ServerThread(socket: Socket) extends Thread("ServerThread") {

  override def run(): Unit = {
    val rand = new Random(100);
    try {
      val out = new DataOutputStream(socket.getOutputStream());
      val in = new ObjectInputStream(new DataInputStream(socket.getInputStream()));

      val filter = in.readObject().asInstanceOf[Int => Boolean];

      while (true) {
        var succeeded = false;
        do {
          val x = rand.nextInt(1000);
          succeeded = filter(x);
          if (succeeded) out.writeInt(x)
        } while (! succeeded);
        Thread.sleep(100)
      }

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
