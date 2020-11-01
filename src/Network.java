import java.util.concurrent.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Network class
 *
 * @author Kerly Titus
 */
public class Network extends Thread {

  /* Maximum number of simultaneous transactions handled by the network buffer */
  private static int maxNbPackets;
  /* Network buffer indices for accessing the input buffer (inputIndexClient, outputIndexServer)
  and output buffer (inputIndexServer, outputIndexClient) */
  private static int inputIndexClient, inputIndexServer, outputIndexServer, outputIndexClient;
  private static String clientIP; /* IP number of the client application*/
  private static String serverIP; /* IP number of the server application */
  private static int portID; /* Port ID of the client application */
  /* Client connection status - connected, disconnected, idle */
  private static String clientConnectionStatus;
  /* Server connection status - connected, disconnected, idle */
  private static String serverConnectionStatus;
  private static Transactions inComingPacket[]; /* Incoming network buffer */
  private static Transactions outGoingPacket[]; /* Outgoing network buffer */
  /* Current status of the network buffers - normal, full, empty */
  private static String inBufferStatus, outBufferStatus;
  private static String networkStatus; /* Network status - active, inactive */

  private static Semaphore inBufferFull;
  private static Semaphore inBufferEmpty;
  private static Semaphore outBufferFull;
  private static Semaphore outBufferEmpty;

  /** Constructor of the Network class */
  Network() {
    int i;

    System.out.println("\n Activating the network ...");
    clientIP = "192.168.2.0";
    serverIP = "216.120.40.10";
    clientConnectionStatus = "idle";
    serverConnectionStatus = "idle";
    portID = 0;
    maxNbPackets = 10;
    inComingPacket = new Transactions[maxNbPackets];
    outGoingPacket = new Transactions[maxNbPackets];
    for (i = 0; i < maxNbPackets; i++) {
      inComingPacket[i] = new Transactions();
      outGoingPacket[i] = new Transactions();
    }
    inBufferStatus = "empty";
    outBufferStatus = "empty";
    inputIndexClient = 0;
    inputIndexServer = 0;
    outputIndexServer = 0;
    outputIndexClient = 0;

    networkStatus = "active";

    inBufferFull = new Semaphore(0);
    inBufferEmpty = new Semaphore(maxNbPackets);
    outBufferFull = new Semaphore(0);
    outBufferEmpty = new Semaphore(maxNbPackets);
  }

  /**
   * Accessor method of Network class
   *
   * @return clientIP
   */
  public static String getClientIP() {
    return clientIP;
  }

  /**
   * Mutator method of Network class
   *
   * @param cip
   */
  public static void setClientIP(String cip) {
    clientIP = cip;
  }

  /**
   * Accessor method of Network class
   *
   * @return serverIP
   */
  public static String getServerIP() {
    return serverIP;
  }

  /**
   * Mutator method of Network class
   *
   * @param sip
   */
  public static void setServerIP(String sip) {
    serverIP = sip;
  }

  /**
   * Accessor method of Network class
   *
   * @return clientConnectionStatus
   */
  public static String getClientConnectionStatus() {
    return clientConnectionStatus;
  }

  /**
   * Mutator method of Network class
   *
   * @param connectStatus
   */
  public static void setClientConnectionStatus(String connectStatus) {
    clientConnectionStatus = connectStatus;
  }

  /**
   * Accessor method of Network class
   *
   * @return serverConnectionStatus
   */
  public static String getServerConnectionStatus() {
    return serverConnectionStatus;
  }

  /**
   * Mutator method of Network class
   *
   * @param connectStatus
   */
  public static void setServerConnectionStatus(String connectStatus) {
    serverConnectionStatus = connectStatus;
  }

  /**
   * Accessor method of Network class
   *
   * @return portID
   */
  public static int getPortID() {
    return portID;
  }

  /**
   * Mutator method of Network class
   *
   * @param pid
   */
  public static void setPortID(int pid) {
    portID = pid;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return inBufferStatus
   */
  public static String getInBufferStatus() {
    return inBufferStatus;
  }

  /**
   * Mutator method of Network class
   *
   * @param inBufStatus
   */
  public static void setInBufferStatus(String inBufStatus) {
    inBufferStatus = inBufStatus;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return outBufferStatus
   */
  public static String getOutBufferStatus() {
    return outBufferStatus;
  }

  /**
   * Mutator method of Network class
   *
   * @param outBufStatus
   */
  public static void setOutBufferStatus(String outBufStatus) {
    outBufferStatus = outBufStatus;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return networkStatus
   */
  public static String getNetworkStatus() {
    return networkStatus;
  }

  /**
   * Mutator method of Network class
   *
   * @param netStatus
   */
  public static void setNetworkStatus(String netStatus) {
    networkStatus = netStatus;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return inputIndexClient
   */
  public static int getInputIndexClient() {
    return inputIndexClient;
  }

  /**
   * Mutator method of Network class
   *
   * @param i1
   */
  public static void setInputIndexClient(int i1) {
    inputIndexClient = i1;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return inputIndexServer
   */
  public static int getInputIndexServer() {
    return inputIndexServer;
  }

  /**
   * Mutator method of Network class
   *
   * @param i2
   */
  public static void setInputIndexServer(int i2) {
    inputIndexServer = i2;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return outputIndexServer
   * @param
   */
  public static int getOutputIndexServer() {
    return outputIndexServer;
  }

  /**
   * Mutator method of Network class
   *
   * @return
   * @param o1
   */
  public static void setOutputIndexServer(int o1) {
    outputIndexServer = o1;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return outputIndexClient
   * @param
   */
  public static int getOutputIndexClient() {
    return outputIndexClient;
  }

  /**
   * Mutator method of Network class
   *
   * @return
   * @param o2
   */
  public static void setOutputIndexClient(int o2) {
    outputIndexClient = o2;
  }

  /**
   * Accessor method of Netowrk class
   *
   * @return maxNbPackets
   * @param
   */
  public static int getMaxNbPackets() {
    return maxNbPackets;
  }

  /**
   * Mutator method of Network class
   *
   * @return
   * @param maxPackets
   */
  public static void setMaxNbPackets(int maxPackets) {
    maxNbPackets = maxPackets;
  }

  /**
   * Transmitting the transactions from the client to the server through the network
   *
   * @return
   * @param inPacket transaction transferred from the client
   */
  public static boolean send(Transactions inPacket) {
    try {
      inBufferEmpty.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    inComingPacket[inputIndexClient].setAccountNumber(inPacket.getAccountNumber());
    inComingPacket[inputIndexClient].setOperationType(inPacket.getOperationType());
    inComingPacket[inputIndexClient].setTransactionAmount(inPacket.getTransactionAmount());
    inComingPacket[inputIndexClient].setTransactionBalance(inPacket.getTransactionBalance());
    inComingPacket[inputIndexClient].setTransactionError(inPacket.getTransactionError());
    inComingPacket[inputIndexClient].setTransactionStatus("transferred");

//    System.out.println("\n DEBUG : Network.send() - index inputIndexClient " + inputIndexClient);
//    System.out.println(
//        "\n DEBUG : Network.send() - account number "
//            + inComingPacket[inputIndexClient].getAccountNumber());

    /* Increment the input buffer index  for the client */
    setInputIndexClient(((getInputIndexClient() + 1) % getMaxNbPackets()));
    /* Check if input buffer is full */
    if (getInputIndexClient() == getOutputIndexServer()) {
      setInBufferStatus("full");

//      System.out.println(
//          "\n DEBUG : Network.send() - inComingBuffer status " + getInBufferStatus());
    } else {
      setInBufferStatus("normal");
    }
    inBufferFull.release();
    return true;
  }

  /**
   * Transmitting the transactions from the server to the client through the network
   *
   * @param outPacket updated transaction received by the client
   */
  public static boolean receive(Transactions outPacket) {
    try {
      outBufferFull.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    outPacket.setAccountNumber(outGoingPacket[outputIndexClient].getAccountNumber());
    outPacket.setOperationType(outGoingPacket[outputIndexClient].getOperationType());
    outPacket.setTransactionAmount(outGoingPacket[outputIndexClient].getTransactionAmount());
    outPacket.setTransactionBalance(outGoingPacket[outputIndexClient].getTransactionBalance());
    outPacket.setTransactionError(outGoingPacket[outputIndexClient].getTransactionError());
    outPacket.setTransactionStatus("done");

//    System.out.println(
//        "\n DEBUG : Network.receive() - index outputIndexClient " + outputIndexClient);
//    System.out.println(
//        "\n DEBUG : Network.receive() - account number " + outPacket.getAccountNumber());

    /* Increment the output buffer index for the client */
    setOutputIndexClient(((getOutputIndexClient() + 1) % getMaxNbPackets()));
    /* Check if output buffer is empty */
    if (getOutputIndexClient() == getInputIndexServer()) {
      setOutBufferStatus("empty");

//      System.out.println(
//          "\n DEBUG : Network.receive() - outGoingBuffer status " + getOutBufferStatus());
    } else {
      setOutBufferStatus("normal");
    }
    outBufferEmpty.release();
    return true;
  }

  /**
   * Transferring the completed transactions from the server to the network buffer
   *
   * @return
   * @param outPacket updated transaction transferred by the server to the network output buffer
   */
  public static boolean transferOut(Transactions outPacket) {
    try {
      outBufferEmpty.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    outGoingPacket[inputIndexServer].setAccountNumber(outPacket.getAccountNumber());
    outGoingPacket[inputIndexServer].setOperationType(outPacket.getOperationType());
    outGoingPacket[inputIndexServer].setTransactionAmount(outPacket.getTransactionAmount());
    outGoingPacket[inputIndexServer].setTransactionBalance(outPacket.getTransactionBalance());
    outGoingPacket[inputIndexServer].setTransactionError(outPacket.getTransactionError());
    outGoingPacket[inputIndexServer].setTransactionStatus("transferred");

//    System.out.println(
//        "\n DEBUG : Network.transferOut() - index inputIndexServer "
//            + inputIndexServer);
//    System.out.println(
//        "\n DEBUG : Network.transferOut() - account number "
//            + outGoingPacket[inputIndexServer].getAccountNumber());

    /* Increment the output buffer index for the server */
    setInputIndexServer(((getInputIndexServer() + 1) % getMaxNbPackets()));
    /* Check if output buffer is full */
    if (getInputIndexServer() == getOutputIndexClient()) {
      setOutBufferStatus("full");

//      System.out.println(
//          "\n DEBUG : Network.transferOut() - outGoingBuffer status " + getOutBufferStatus());
    } else {
      setOutBufferStatus("normal");
    }
    outBufferFull.release();
    return true;
  }

  /**
   * Transferring the transactions from the network buffer to the server
   *
   * @param inPacket transaction transferred from the input buffer to the server
   */
  public static boolean transferIn(Transactions inPacket) {
    try{
      inBufferFull.acquire();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    inPacket.setAccountNumber(inComingPacket[outputIndexServer].getAccountNumber());
    inPacket.setOperationType(inComingPacket[outputIndexServer].getOperationType());
    inPacket.setTransactionAmount(inComingPacket[outputIndexServer].getTransactionAmount());
    inPacket.setTransactionBalance(inComingPacket[outputIndexServer].getTransactionBalance());
    inPacket.setTransactionError(inComingPacket[outputIndexServer].getTransactionError());
    inPacket.setTransactionStatus("received");

//    System.out.println(
//        "\n DEBUG : Network.transferIn() - index outputIndexServer " + outputIndexServer);
//    System.out.println(
//        "\n DEBUG : Network.transferIn() - account number " + inPacket.getAccountNumber());

    /* Increment the input buffer index for the server */
    setOutputIndexServer(((getOutputIndexServer() + 1) % getMaxNbPackets()));
    /* Check if input buffer is empty */
    if (getOutputIndexServer() == getInputIndexClient()) {
      setInBufferStatus("empty");

//      System.out.println(
//          "\n DEBUG : Network.transferIn() - inComingBuffer status " + getInBufferStatus());
    } else {
      setInBufferStatus("normal");
    }
    inBufferEmpty.release();
    return true;
  }

  /**
   * Handling of connection requests through the network
   *
   * @return valid connection
   * @param IP
   */
  public static boolean connect(String IP) {
    if (getNetworkStatus().equals("active")) {
      if (getClientIP().equals(IP)) {
        setClientConnectionStatus("connected");
        setPortID(0);
      } else if (getServerIP().equals(IP)) {
        setServerConnectionStatus("connected");
      }
      return true;
    } else return false;
  }

  /**
   * Handling of disconnection requests through the network
   *
   * @return valid disconnection
   * @param IP
   */
  public static boolean disconnect(String IP) {
    if (getNetworkStatus().equals("active")) {
      if (getClientIP().equals(IP)) {
        setClientConnectionStatus("disconnected");
      } else if (getServerIP().equals(IP)) {
        setServerConnectionStatus("disconnected");
      }
      return true;
    } else return false;
  }

  /**
   * Create a String representation based on the Network Object
   *
   * @return String representation
   */
  public String toString() {
    return ("\n Network status "
        + getNetworkStatus()
        + "Input buffer "
        + getInBufferStatus()
        + "Output buffer "
        + getOutBufferStatus());
  }

  /**
   * Code for the run method
   *
   */
  public void run() {
//    System.out.println("\n DEBUG : Network.run() - starting network thread");

    while (!getClientConnectionStatus().equals("disconnected")
        || !getServerConnectionStatus().equals("disconnected")) {
      Thread.yield();
    }
  }
}
