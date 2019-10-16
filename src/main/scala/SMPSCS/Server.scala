package SMPSCS

import java.io.IOException
import java.net.ServerSocket

/**
  * Simple client/server application using Java sockets.
  *
  * The server simply generates random integer values and
  * the clients provide a filter function to the server
  * to get only values they interested in (eg. even or
  * odd values, and so on).
  */


object Server {

  def main(args: Array[String]): Unit = {
    try {
      val listener = new ServerSocket(9999);
      while (true)
        new ServerThread(listener.accept()).start();
      listener.close()
    }
    catch {
      case e: IOException =>
        System.err.println("Could not listen on port: 9999.");
        System.exit(-1)
    }
  }

}
