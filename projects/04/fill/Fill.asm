// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(LOOP)
    @SCREEN
    D=A
    @curr
    M=D
    @KBD
    D=M
    @UNPRESSED
    D;JEQ //if M[KBD] == 0, goto UNPRESSED
    @PRESSED
    0;JMP //else goto PRESSED

(UNPRESSED)
    @KBD
    D=A
    @curr
    D=D-A
    @LOOP
    D;JEQ
    @curr
    A=M
    M=0
    @curr
    M=M+1
    @UNPRESSED
    0;JMP

(PRESSED)
    @KBD
    D=A
    @curr
    D=D-A
    @LOOP
    D;JEQ
    @curr
    A=M
    M=-1
    @curr
    M=M+1
    @PRESSED
    0;JMP