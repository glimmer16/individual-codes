import jieba.analyse
import codecs

f = codecs.open('data.txt', 'r', encoding='utf8')
target = codecs.open('data_1.txt', 'w', encoding='utf8')
stopkey = [w.strip() for w in codecs.open('stopWord.txt', 'r', encoding='utf-8').readlines()]
print("open files")

lineNum = 1
line = f.readline()
while line:
    print("---processiong", lineNum, "article---")
    seg_list = jieba.cut(line, cut_all = False)
    line_seg = ''
    for word in seg_list:
        if word not in stopkey:
           line_seg += word
           line_seg += ' '

    target.writelines(line_seg)
    lineNum += 1
    line = f.readline()
 
print("well done")
f.close()
target.close()