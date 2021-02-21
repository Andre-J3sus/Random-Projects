from tkinter import *

# Defining the screen
screen = Tk()
screen.title("Calculator - by Jesus")  # Title
screen["bg"] = "royalblue2"  # Background color
screen.resizable(width=False, height=False)  # Resizable in Height but not in Width


# Clicking the buttons
def put_number(char):
    global expression
    if char == "C":
        expression = ""
        text.delete(0, END)

    elif char == "=":
        text.delete(0, END)
        try:
            expression = str(int(eval(expression)))  # eval() function evaluate the string to int
        except ZeroDivisionError:
            expression = "Zero Division Error"
        text.insert(0, expression)

    else:
        if expression == "Zero Division Error":
            expression = ""
            text.delete(0, END)
        expression += str(char)
        text.insert(END, char)  # Insert number in the end


expression = ""  # Expression with the equation

# Text Entry
text = Entry(screen, width=18, borderwidth=5, font=("arial bold", 20))
text.grid(row=0, column=0, columnspan=4, padx=10, pady=10)

# Defining buttons
btn1 = Button(screen, text="1", padx=20, pady=20, command=lambda: put_number(1), font=("arial bold", 20))
btn2 = Button(screen, text="2", padx=20, pady=20, command=lambda: put_number(2), font=("arial bold", 20))
btn3 = Button(screen, text="3", padx=20, pady=20, command=lambda: put_number(3), font=("arial bold", 20))
btn4 = Button(screen, text="4", padx=20, pady=20, command=lambda: put_number(4), font=("arial bold", 20))
btn5 = Button(screen, text="5", padx=20, pady=20, command=lambda: put_number(5), font=("arial bold", 20))
btn6 = Button(screen, text="6", padx=20, pady=20, command=lambda: put_number(6), font=("arial bold", 20))
btn7 = Button(screen, text="7", padx=20, pady=20, command=lambda: put_number(7), font=("arial bold", 20))
btn8 = Button(screen, text="8", padx=20, pady=20, command=lambda: put_number(8), font=("arial bold", 20))
btn9 = Button(screen, text="9", padx=20, pady=20, command=lambda: put_number(9), font=("arial bold", 20))
btn0 = Button(screen, text="0", padx=20, pady=20, command=lambda: put_number(0), font=("arial bold", 20))
btnC = Button(screen, text="C", padx=17.4, pady=20, command=lambda: put_number("C"), font=("arial bold", 20), bg="azure")
btnE = Button(screen, text="=", padx=19, pady=20, command=lambda: put_number("="), font=("arial bold", 20), bg="royalblue2")
btnAdd = Button(screen, text="+", padx=20, pady=20, command=lambda: put_number("+"), font=("arial bold", 20), bg="azure")
btnMinus = Button(screen, text="-", padx=23.4, pady=20, command=lambda: put_number("-"), font=("arial bold", 20), bg="azure")
btnMult = Button(screen, text="x", padx=22, pady=20, command=lambda: put_number("*"), font=("arial bold", 20), bg="azure")
btnDiv = Button(screen, text="÷", padx=20.4, pady=20, command=lambda: put_number("/"), font=("arial bold", 20), bg="azure")

# Placing buttons in screen
btn1.grid(row=3, column=0)
btn2.grid(row=3, column=1)
btn3.grid(row=3, column=2)
btn4.grid(row=2, column=0)
btn5.grid(row=2, column=1)
btn6.grid(row=2, column=2)
btn7.grid(row=1, column=0)
btn8.grid(row=1, column=1)
btn9.grid(row=1, column=2)
btn0.grid(row=4, column=1)
btnC.grid(row=4, column=0)
btnE.grid(row=4, column=2)
btnAdd.grid(row=1, column=3)
btnMinus.grid(row=2, column=3)
btnMult.grid(row=3, column=3)
btnDiv.grid(row=4, column=3)

screen.mainloop()
