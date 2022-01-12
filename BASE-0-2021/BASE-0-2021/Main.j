.class public Main
.super java/lang/Object
;
; standard initializer
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method

.method public static main([Ljava/lang/String;)V
       ; set limits used by this method
       .limit locals 10 
       .limit stack 256

	; setup local variables:

       ;    1 - the PrintStream object held in java.lang.System.out
       getstatic java/lang/System/out Ljava/io/PrintStream;

       ; place your bytecodes here between START and END
       ; START

	aconst_null
	astore_3
	new frame_0
	dup
	invokespecial frame_0/<init>()V
	dup
	aload_3
	putfield frame_0/sl Ljava/lang/Object;
	astore_3
	aload_3
	new ref_I
	dup
	invokespecial ref_I/<init>()V
	dup
	sipush 0
	putfield ref_I/v I
	putfield frame_0/x Ljava/lang/Object;
	aload_3
	new ref_I
	dup
	invokespecial ref_I/<init>()V
	dup
	sipush 0
	putfield ref_I/v I
	putfield frame_0/s Ljava/lang/Object;
	pop
	L1:
	aload_3
	getfield frame_0/x Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	sipush 10
	isub
	ifle L3
	sipush 0
	goto L4
	L3:
 sipush 1
	L4:
	ifeq L2
	aload_3
	getfield frame_0/s Ljava/lang/Object;
	checkcast ref_I
	aload_3
	getfield frame_0/s Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	aload_3
	getfield frame_0/x Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	iadd
	putfield ref_I/v I
	aload_3
	getfield frame_0/x Ljava/lang/Object;
	checkcast ref_I
	aload_3
	getfield frame_0/x Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	sipush 1
	iadd
	putfield ref_I/v I
	goto L1
	L2:
	getstatic java/lang/System/out Ljava/io/PrintStream;
	aload_3
	getfield frame_0/s Ljava/lang/Object;
	checkcast ref_I
	getfield ref_I/v I
	invokestatic java/lang/String/valueOf(I)Ljava/lang/String;
	invokevirtual java/io/PrintStream/println(Ljava/lang/String;)V
	aload_3
	getfield frame_0/sl Ljava/lang/Object;
	astore_3
       
          
       ; END

    return
.end method

