from tkinter import *
from random import choice, randrange

# Defining the screen
screen = Tk()
screen.title("Password Generator - by Jesus")  # Title
screen["bg"] = "grey"  # Background color

# Monitor size:
monitor_width = screen.winfo_screenwidth()
monitor_height = screen.winfo_screenheight()
screen_width = 400
screen_height = 500

screen_pos = monitor_width//2 - screen_width//2, monitor_height//2 - screen_height//2

screen.geometry("{}x{}+{}+{}".format(screen_width, screen_height, screen_pos[0], screen_pos[1]))
screen.resizable(width=False, height=True)  # Resizable in Height but not in Width

# Screen min and max size
screen.minsize(width=screen_width, height=screen_height)
screen.maxsize(width=screen_width, height=monitor_height)

optionsToAdd = ["Lowercase Letters", "Uppercase Letters", "Numbers", "Symbols"]
optionsChosen = []
password = ""

lowercaseLetters = "abcdefghijklmnopqrstuvwxyz"
uppercaseLetters = lowercaseLetters.upper()
symbols = "!#$%&/()=?»«'}][{€§£@|\"<>+*"
numbers = range(0, 11)

title = Label(screen, text="Password Generator", font=("times", 30))
title.pack()
subtitle = Label(screen, text="by Jesus", font=("times", 15))
subtitle.pack()

check_btns = []
vars = []
for option in optionsToAdd:
    var = IntVar()
    vars.append(var)
    newButton = Checkbutton(screen, text=option, width=15, anchor=W, bg="white", font=("helvetica", 15), variable=var, onvalue=1, offvalue=0)
    check_btns.append(newButton)
    newButton.place(x=screen_width//2-8, y=screen_height//4 + len(check_btns)*30, anchor=CENTER)

number_of_chars = Entry(screen, width=10)
label = Label(screen, text="Number of characters:", width=18, height=1)
label.place(x=screen_width//2-45, y=screen_height//5, anchor=E)
number_of_chars.place(x=screen_width//2-5, y=screen_height//5, anchor=CENTER)


def generate_password():
    global password, optionsChosen
    if number_of_chars.get().isdigit():
        password = ""
        optionsChosen = []
        advice = Label(screen, text="Add some character type first!")

        for idx, check_btn in enumerate(check_btns):
            if vars[idx].get() == 1 and check_btn.cget("text") not in optionsChosen:
                optionsChosen.append(check_btn.cget("text"))

        while len(password) < int(number_of_chars.get()):
            if len(optionsChosen) == 0:
                advice.place(x=screen_width//2, y=5*screen_height//6)
                break
            else:
                advice.destroy()
                for option in optionsChosen:
                    if option == "Lowercase Letters":
                        password += choice(lowercaseLetters)
                    elif option == "Uppercase Letters":
                        password += choice(uppercaseLetters)
                    elif option == "Numbers":
                        password += str(randrange(0, 11))
                    elif option == "Symbols":
                        password += choice(symbols)

        pass_label = Label(screen, text=password)
        pass_label.place(x=screen_width // 8 + 70, y=4 * screen_height // 5)


pass_title_label = Label(screen, text="Password:", width=10)
pass_title_label.place(x=screen_width // 8 - 15, y=4 * screen_height // 5)

create_btn = Button(screen, text="GENERATE", command=generate_password, width=20, font=("times", 15))
create_btn.place(x=screen_width//2-8, y=3*screen_height//5, anchor=CENTER)

screen.mainloop()
