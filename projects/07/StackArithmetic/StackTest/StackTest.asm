@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$0
D;JEQ
@SP
A=M
M=0
@LABEL$1
0;JMP
(LABEL$0)
@SP
A=M
M=-1
(LABEL$1)
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$2
D;JEQ
@SP
A=M
M=0
@LABEL$3
0;JMP
(LABEL$2)
@SP
A=M
M=-1
(LABEL$3)
@SP
M=M+1
@16
D=A
@SP
A=M
M=D
@SP
M=M+1
@17
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$4
D;JEQ
@SP
A=M
M=0
@LABEL$5
0;JMP
(LABEL$4)
@SP
A=M
M=-1
(LABEL$5)
@SP
M=M+1
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$6
D;JLT
@SP
A=M
M=0
@LABEL$7
0;JMP
(LABEL$6)
@SP
A=M
M=-1
(LABEL$7)
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@892
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$8
D;JLT
@SP
A=M
M=0
@LABEL$9
0;JMP
(LABEL$8)
@SP
A=M
M=-1
(LABEL$9)
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@891
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$10
D;JLT
@SP
A=M
M=0
@LABEL$11
0;JMP
(LABEL$10)
@SP
A=M
M=-1
(LABEL$11)
@SP
M=M+1
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$12
D;JGT
@SP
A=M
M=0
@LABEL$13
0;JMP
(LABEL$12)
@SP
A=M
M=-1
(LABEL$13)
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$14
D;JGT
@SP
A=M
M=0
@LABEL$15
0;JMP
(LABEL$14)
@SP
A=M
M=-1
(LABEL$15)
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
D=M-D
@LABEL$16
D;JGT
@SP
A=M
M=0
@LABEL$17
0;JMP
(LABEL$16)
@SP
A=M
M=-1
(LABEL$17)
@SP
M=M+1
@57
D=A
@SP
A=M
M=D
@SP
M=M+1
@31
D=A
@SP
A=M
M=D
@SP
M=M+1
@53
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M+D
@SP
M=M+1
@112
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M-D
@SP
M=M+1
@SP
M=M-1
A=M
M=-M
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M&D
@SP
M=M+1
@82
D=A
@SP
A=M
M=D
@SP
M=M+1
@SP
M=M-1
A=M
D=M
@SP
M=M-1
A=M
M=M|D
@SP
M=M+1
@SP
M=M-1
A=M
M=!M
@SP
M=M+1
