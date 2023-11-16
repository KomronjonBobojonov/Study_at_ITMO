from tasks import task1, task3

#408281 % 6 = 5
#408281 % 4 = 1
#408281 % 7 = 6

task = input("Enter task number (1-3) you want to run: ")
if task == '1':
    print(task1.solve(input("Enter your string to find the number of emoticons: ")))
elif task == '3':
    print(task3.solve(input("Enter your string to find words with one vowel letter: ")))
else:
    print("Can't resolve task number")
    
