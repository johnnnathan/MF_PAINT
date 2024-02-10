from PIL import Image
from cffi.backend_ctypes import unicode

file = open("C:\\Users\\Dimitris T\\Desktop\\Projects\\MF_PAINT\\ASCIIFile.txt", "r")

totalList = []
lines = file.readlines()

print(lines)
file.close()
amountofrows = len(lines)
amountofcollums = len(lines[0].split())




image = Image.new('RGB', [amountofrows,amountofcollums], 255)
data = image.load()

for x in range(amountofrows):
    elements_of_line = lines[x].split()
    for y in range(amountofcollums):
        # print(elements_of_line)
        element = elements_of_line[y].encode("utf-8")
        element = int(element)
        element-=160
        print(element)
        data[x,y] = (element, element, element)

image.save("Image.png")