package SMPSCS

import java.io.IOException
import java.net.ServerSocket


object Server extends App {
  private val PORT: Int = 5678;
  try {
    val listener = new ServerSocket(PORT);
    while (true)
      new ServerThread(listener.accept()).start();
    listener.close()
  }
  catch {
    case e: IOException =>
      System.err.println("Could not listen on port:" + PORT + ".\\n" + e);
      System.exit(-1)
  }


}
