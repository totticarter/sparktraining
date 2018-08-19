http://go-on.iteye.com/blog/1673894
http://www.cnblogs.com/zhengyun_ustc/archive/2013/01/06/dumpanalysis.html

# JstackEntry

"B" prio=10 tid=0x0969a000 nid=0x11d6 waiting for monitor entry [0x8bb22000]
   java.lang.Thread.State: BLOCKED (on object monitor)
    at org.marshal.MyThread.run(MyThread.java:7)
    - waiting to lock <0x94757078> (a org.marshal.MyThread)
    at java.lang.Thread.run(Thread.java:636)

"A" prio=10 tid=0x09698800 nid=0x11d5 runnable [0x8bb73000]
   java.lang.Thread.State: RUNNABLE
    at java.io.FileOutputStream.writeBytes(Native Method)
    at java.io.FileOutputStream.write(FileOutputStream.java:297)
    at java.io.BufferedOutputStream.flushBuffer(BufferedOutputStream.java:82)
    at java.io.BufferedOutputStream.flush(BufferedOutputStream.java:140)
    - locked <0x947571b0> (a java.io.BufferedOutputStream)
    at java.io.PrintStream.write(PrintStream.java:449)
    - locked <0x94757190> (a java.io.PrintStream)
    at sun.nio.cs.StreamEncoder.writeBytes(StreamEncoder.java:220)
    at sun.nio.cs.StreamEncoder.implFlushBuffer(StreamEncoder.java:290)
    at sun.nio.cs.StreamEncoder.flushBuffer(StreamEncoder.java:103)
    - locked <0x947572a0> (a java.io.OutputStreamWriter)
    at java.io.OutputStreamWriter.flushBuffer(OutputStreamWriter.java:185)
    at java.io.PrintStream.write(PrintStream.java:494)
    - locked <0x94757190> (a java.io.PrintStream)
    at java.io.PrintStream.print(PrintStream.java:636)
    at java.io.PrintStream.println(PrintStream.java:773)
    - locked <0x94757190> (a java.io.PrintStream)
    at org.marshal.MyThread.run(MyThread.java:8)
    - locked <0x94757078> (a org.marshal.MyThread)
    at java.lang.Thread.run(Thread.java:636)

# JstackWait

  "B" prio=10 tid=0x08173000 nid=0x1304 in Object.wait() [0x8baf2000]
     java.lang.Thread.State: WAITING (on object monitor)
      at java.lang.Object.wait(Native Method)
      - waiting on <0xa9cb50e0> (a org.marshal.WaitThread)
      at java.lang.Object.wait(Object.java:502)
      at org.marshal.WaitThread.run(WaitThread.java:8)
      - locked <0xa9cb50e0> (a org.marshal.WaitThread)
      at java.lang.Thread.run(Thread.java:636)

  "A" prio=10 tid=0x08171c00 nid=0x1303 in Object.wait() [0x8bb43000]
     java.lang.Thread.State: WAITING (on object monitor)
      at java.lang.Object.wait(Native Method)
      - waiting on <0xa9cb50e0> (a org.marshal.WaitThread)
      at java.lang.Object.wait(Object.java:502)
      at org.marshal.WaitThread.run(WaitThread.java:8)
      - locked <0xa9cb50e0> (a org.marshal.WaitThread)
      at java.lang.Thread.run(Thread.java:636)
## 说明
A和B线程都进入了”wait set“。B线程也拿到过这个Monitor，因为A线程释放过了，这也验证上面的话，他们都在等待得而复失的<0xa9cb50e0>。

# JstackDeaclock
"Attach Listener" #14 daemon prio=9 os_prio=31 tid=0x00007f8c3382f800 nid=0xa07 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"DestroyJavaVM" #13 prio=5 os_prio=31 tid=0x00007f8c3600f000 nid=0x2603 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"B" #12 prio=5 os_prio=31 tid=0x00007f8c3600e800 nid=0x5703 waiting for monitor entry [0x0000700010964000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.method_B(JstackDeaclock.java:23)
        - waiting to lock <0x000000076b0dd240> (a java.lang.Object)
        - locked <0x000000076b0dd250> (a java.lang.Object)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.run(JstackDeaclock.java:31)
        at java.lang.Thread.run(Thread.java:748)

"A" #11 prio=5 os_prio=31 tid=0x00007f8c360b3800 nid=0x5603 waiting for monitor entry [0x0000700010861000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.method_A(JstackDeaclock.java:15)
        - waiting to lock <0x000000076b0dd250> (a java.lang.Object)
        - locked <0x000000076b0dd240> (a java.lang.Object)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.run(JstackDeaclock.java:30)
        at java.lang.Thread.run(Thread.java:748)

"Monitor Ctrl-Break" #10 daemon prio=5 os_prio=31 tid=0x00007f8c349b8000 nid=0x5503 runnable [0x000070001075e000]
   java.lang.Thread.State: RUNNABLE
        at java.net.PlainSocketImpl.socketAccept(Native Method)
        at java.net.AbstractPlainSocketImpl.accept(AbstractPlainSocketImpl.java:409)
        at java.net.ServerSocket.implAccept(ServerSocket.java:545)
        at java.net.ServerSocket.accept(ServerSocket.java:513)
        at com.intellij.rt.execution.application.AppMain$1.run(AppMain.java:90)
        at java.lang.Thread.run(Thread.java:748)

"Service Thread" #9 daemon prio=9 os_prio=31 tid=0x00007f8c3401a000 nid=0x3c03 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread3" #8 daemon prio=9 os_prio=31 tid=0x00007f8c36003800 nid=0x3b03 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread2" #7 daemon prio=9 os_prio=31 tid=0x00007f8c37023000 nid=0x3a03 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread1" #6 daemon prio=9 os_prio=31 tid=0x00007f8c36003000 nid=0x4203 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"C2 CompilerThread0" #5 daemon prio=9 os_prio=31 tid=0x00007f8c37022800 nid=0x4403 waiting on condition [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=31 tid=0x00007f8c36001000 nid=0x4607 runnable [0x0000000000000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=31 tid=0x00007f8c3682e800 nid=0x4c03 in Object.wait() [0x000070000ff46000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x000000076ab08ed0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
        - locked <0x000000076ab08ed0> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:212)

"Reference Handler" #2 daemon prio=10 os_prio=31 tid=0x00007f8c34010000 nid=0x4d03 in Object.wait() [0x000070000fe43000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x000000076ab06bf8> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x000000076ab06bf8> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"VM Thread" os_prio=31 tid=0x00007f8c3380c000 nid=0x2f03 runnable

"GC task thread#0 (ParallelGC)" os_prio=31 tid=0x00007f8c36803000 nid=0x2207 runnable

"GC task thread#1 (ParallelGC)" os_prio=31 tid=0x00007f8c36803800 nid=0x2003 runnable

"GC task thread#2 (ParallelGC)" os_prio=31 tid=0x00007f8c37000800 nid=0x2a03 runnable

"GC task thread#3 (ParallelGC)" os_prio=31 tid=0x00007f8c34008800 nid=0x2c03 runnable

"GC task thread#4 (ParallelGC)" os_prio=31 tid=0x00007f8c34009800 nid=0x5403 runnable

"GC task thread#5 (ParallelGC)" os_prio=31 tid=0x00007f8c37001800 nid=0x5203 runnable

"GC task thread#6 (ParallelGC)" os_prio=31 tid=0x00007f8c33000000 nid=0x5003 runnable

"GC task thread#7 (ParallelGC)" os_prio=31 tid=0x00007f8c33800800 nid=0x4e03 runnable

"VM Periodic Task Thread" os_prio=31 tid=0x00007f8c33819800 nid=0x3f03 waiting on condition

JNI global references: 17


Found one Java-level deadlock:
//=============================
"B":
  waiting to lock monitor 0x00007f8c34015f58 (object 0x000000076b0dd240, a java.lang.Object),
  which is held by "A"
"A":
  waiting to lock monitor 0x00007f8c340134b8 (object 0x000000076b0dd250, a java.lang.Object),
  which is held by "B"

Java stack information for the threads listed above:
//===================================================
"B":
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.method_B(JstackDeaclock.java:23)
        - waiting to lock <0x000000076b0dd240> (a java.lang.Object)
        - locked <0x000000076b0dd250> (a java.lang.Object)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.run(JstackDeaclock.java:31)
        at java.lang.Thread.run(Thread.java:748)
"A":
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.method_A(JstackDeaclock.java:15)
        - waiting to lock <0x000000076b0dd250> (a java.lang.Object)
        - locked <0x000000076b0dd240> (a java.lang.Object)
        at com.tencent.cubeli.java.jvm.jstack.JstackDeaclock.run(JstackDeaclock.java:30)
        at java.lang.Thread.run(Thread.java:748)

Found 1 deadlock.
