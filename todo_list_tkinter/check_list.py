from tkinter import *
import tkinter.messagebox
import pickle

# Defining the screen
screen = Tk()
screen.title("Task List - by Jesus")  # Title
screen["bg"] = "royalblue1"  # Background color
screen.resizable(width=False, height=True)  # Resizable in Height but not in Width


# Create frame to list of tasks and scrollbar
frame_tasks = Frame(screen)


# Items list
list_of_items = Listbox(frame_tasks, width=35, font=("arial", 15), height=10, selectmode='multiple')


# Adding scrollbar
scrollbar = Scrollbar(frame_tasks)
list_of_items.config(yscrollcommand=scrollbar.set)
scrollbar.config(command=list_of_items.yview)


# Text box to user input
text_box = Entry(screen, width=30, borderwidth=5, font=("arial", 15))
text_box.bind("<FocusIn>", lambda args: text_box.delete('0', 'end'))  # Deleting the placeholder when clicking to text
text_box.insert(0, "Your task here!")   # Placeholder


# Adding a new item to the list
def addItem():
    task = text_box.get()
    if task == "Your task here!" or task.strip() == "":  # Warning if its empty
        tkinter.messagebox.showwarning(title="Warning!", message="You must enter a task first!")
    else:
        list_of_items.insert(END, task + ";")
        text_box.delete(0, END)


# Deleting the selected item
def delItem():
    items_selected_idx = list_of_items.curselection()
    for idx in items_selected_idx:
        list_of_items.delete(idx)


# Saving items
def saveItems():
    items = list_of_items.get(0, END)
    pickle.dump(items, open("items.dat", "wb"))


# Loading items
def loadItems():
    try:
        items = pickle.load(open("items.dat", "rb"))
        list_of_items.delete(0, END)
        for item in items:
            list_of_items.insert(END, item)
    except:
        tkinter.messagebox.showwarning(title="Warning!", message="Cannot find saved tasks!")


# Buttons
add_item_btn = Button(screen, text="Add Item", bg="white", font=('arial', 10), command=addItem, width=30)

del_item_btn = Button(screen, text="Delete Item", bg="white", font=('arial', 10), command=delItem, width=30)

save_items_btn = Button(screen, text="Save Items", bg="white", font=('arial', 10), command=saveItems, width=30)

load_items_btn = Button(screen, text="Load Items", bg="white", font=('arial', 10), command=loadItems, width=30)


# Packing
text_box.pack()
add_item_btn.pack()
del_item_btn.pack()
save_items_btn.pack()
load_items_btn.pack()
# List of items in the Left side of the frame and scrollbar in the right side
frame_tasks.pack()
list_of_items.pack(side=LEFT)
scrollbar.pack(side=RIGHT, fill=Y)


screen.mainloop()
