# Java并发编程

## 线程

Java天生多线程，执行main()方法的是main线程。一个Java程序通常是多个线程在运行。

### 线程优先级

Java程序的正确性不能依赖于线程的优先级高低

### 线程状态

| 状态         | 说明                           |
| ------------ | ------------------------------ |
| NEW          | 被创建，还未调用start          |
| RUNNABLE     | 运行状态                       |
| BLOCKED      | 阻塞于锁                       |
| WAITING      | 等待，需要其他线程的通知或中断 |
| TIME_WAITING | 超时等待，可以自行返回         |
| TERMINATED   | 终止，执行完毕                 |

![Thread-State](D:\Java\MultipleProgram\src\chap4\Thread-State.png)

Java将操作系统中运行和就绪统称为运行状态，阻塞态是线程进入synchronized域获取锁时的状态。

### Daemon线程

一种支持型线程，主要用作程序后台调度和支持性工作。JVM不存在非Daemon线程时候将退出。Daemon属性需要在启动线程前设置。

JVM退出时Daemon线程中的finally不一定会执行。所以不能靠Daemon线程的finally块确保资源的关闭和清理。

## 线程启动和终止

### 构造线程

运行线程前需要构造一个线程对象，可以参考Thread的init方法

### 启动线程

start的含义是：当前线程同步告知JVM，只要线程规划器空闲，应立即启动调用start()方法的线程。

启动线程前最好设置名称，利于jstack等工具的分析

### 中断

线程通过isInterrupted()来判断是否被终端。也可以用Thread.interrupted()对中断标志位进行复位.

### 安全终止线程

## 线程间通信

### volatile  &  synchronized

Java支持多线程同时访问一个对象,每个线程可以拥有该变量的一个拷贝，所以执行过程中，一个线程看到的变量不一定是最新的。

**volatile**:任何对该变量的访问均需要从共享内存中读取，而对它的改变必须同步刷新回共享内存。可以保证所有线程对变量访问的可见性。

**synchronized**：修饰方法或者同步块,对于同步块的实现使用了*monitorenter*和*monitorexit*，同步方法依靠方法修饰符上的ACC_SYNCHRONIZED完成。本质上是对一个对象的监视器的获取，此过程排他。

任意对象都有自己的监视器，没有获取到监视器的线程会阻塞在同步块入口处，进入BLOCKED状态。

![Monitor](D:\Java\MultipleProgram\src\chap4\Monitor.png)

任意线程对Object(受synchronized保护)的访问，首先要获得监视器，失败则进入同步队列。前驱释放锁后，该操作唤醒阻塞在同步队列中的线程，重新尝试对锁的获取。

### 等待/通知机制

* 使用wait,notify前需要先对调用对象加锁。
* 调用wait后，状态由RUNNING变为WAITING，并将线程放置到对象的等待队列。
* 调用notify后，等待线程依旧不会返回，需要等调用notify的线程释放锁以后。
* 从wait返回的前提是获得了调用对象的锁。

![WaitNotify](D:\Java\MultipleProgram\src\chap4\WaitNotify.png)

### 等待/通知的经典范式

消费者: 获取对象的锁——>若条件不满足，wait，被通知就任然要检查——>条件满足则执行相应逻辑.

```java
synchronized(obj){
    while(!condition){
        obj.wait();
    }
    //TODO
}
```

生产者：获得锁——>改变条件——>notify

```java
synchronized(obj){
    changeCondition();
    obj.notifyAll();
}
```

### 管道输入/输出流

主要用于线程之间的数据传输，传输介质为内存。

PipedOutputStream,PipedInputStream,PipedReader,PipedWriter

Piped流在使用前必须绑定

### join

A执行thread.join()：当前线程A等待thread线程终止后才从join返回。

### ThreadLocal(线程变量)

以ThreadLocal对象为键，任意对象为值的存储结构。此结构被附带在线程上，即一个线程可以根据ThreadLocal对象查询到绑定在该线程上的一个值。

