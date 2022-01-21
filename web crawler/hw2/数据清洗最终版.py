#!/usr/bin/env python
# coding: utf-8

# In[77]:


import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import jieba
import jieba.posseg as pseg
import glob
import random
import re
import string
from sklearn.feature_extraction.text import TfidfVectorizer
#from keras import models
#from keras import layers
#from keras.preprocessing.text import Tokenizer
get_ipython().run_line_magic('matplotlib', 'inline')


# In[58]:


def stopwordslist():#加载停用词表
    stopwords = [line.strip() for line in open('stopwords.txt',encoding='utf-8').readlines()]
    return stopwords


# In[3]:


def deleteStop(sentence):    #去停用词
    stopwords = stopwordslist()
    outstr=""
    for word in sentence:
        if word not in stopwords and word!="\n":
            outstr+=word
    return outstr


# In[4]:


def wordcut(Review):
    Mat=[]
    for rec in Review:
        seten=[]
        rec=re.sub('[%s]'%re.escape(string.punctuation),'',rec)
        fenci=jieba.lcut(rec)   #精准模式分词
        stc=deleteStop(fenci)    #去停用词
        seg_list=pseg.cut(stc)   #标注词性
        for word,flag in seg_list:
            if flag not in ["nr","ns","nt","m","f","ul","l","r","t"]:
                seten.append(word)
        Mat.append(seten)
    return Mat


# In[5]:


df=pd.read_csv('data.csv')
df.head()


# In[6]:


#查看数据形状
df.shape


# In[7]:


#快速了解数据的结构
df.info()


# In[8]:


#查看每一列的标题
df.columns


# In[9]:


df.productId


# In[10]:


#查看有没有两行完全一样的数据
df.duplicated()


# In[11]:


#把两行完全一样的数据显示出来
df[df.duplicated()]


# In[12]:


#查看有没有questionId完全一样的两行
df.duplicated(subset=['questionId'])


# In[13]:


#把questionId完全一样的两行显示出来
df[df.duplicated(subset=['questionId'])]


# In[14]:


#查看所有带数字的信息
df.describe().T


# In[15]:


#sta=df['productId']


# In[16]:


#把小于七位数的productId找出来
#df[sta.abs()<1e+06].index


# In[17]:


#把小于七位数的productId所在行删除
#df.drop(df[sta.abs()<1e+06].index,inplace=True)


# In[18]:


#查看数据形状
df.shape


# In[19]:


#把空的信息找出来
df.isnull().sum()


# In[20]:


#找出questions空信息所在的行
df[df.questions.isnull()].index


# In[21]:


#把questions空的行删除
df.drop(df[df.questions.isnull()].index,inplace=True)


# In[22]:


#查看数据形状
df.shape


# In[23]:


col=df.questions.values


# In[24]:


#删除questions列的所有空格
df.questions=[x.strip() for x in col]
df.questions


# In[25]:


#查看数据结构
df.info()


# In[26]:


#查看带有？符号的问题
df[df['questions'].str.contains("\?")]


# In[27]:


#去除问题中的问号
df['questions'].replace('\?','',regex=True,inplace=True)


# In[28]:


#查看是否已经去除干净
df[df['questions'].str.contains("\?")]


# In[29]:


#找出含有♀符号的问题
df[df['questions'].str.contains("♀")]


# In[30]:


#删除问题中所有♀符号
df['questions'].replace('♀','',regex=True,inplace=True)


# In[31]:


#删除问题中各种特殊符号
df['questions'].replace('!','',regex=True,inplace=True)
df['questions'].replace('@','',regex=True,inplace=True)
df['questions'].replace('Q_Q','',regex=True,inplace=True)
df['questions'].replace('_','',regex=True,inplace=True)
df['questions'].replace('^','',regex=True,inplace=True)
df['questions'].replace('你好','',regex=True,inplace=True)
df['questions'].replace('在吗','',regex=True,inplace=True)
df['questions'].replace('\n','',regex=True,inplace=True)
df['questions'].replace('(`н′)','',regex=True,inplace=True)
df['questions'].replace('╭(╯3╰)╮','',regex=True,inplace=True)


# In[32]:


#df[df['questions'].str.len()<4].index
#找出问题中全是数字的索引
df[df['questions'].str.isdecimal()].index


# In[33]:


#删除问题中全是数字的行
df.drop(df[df['questions'].str.isdecimal()].index,inplace=True)


# In[34]:


#删除问题中小于三个字的行
df.drop(df[df['questions'].str.len()<3].index,inplace=True)


# In[35]:


#df[df['questions'].str.encode('UTF-8').isalpha()]
#查看数据结构
df.shape


# In[36]:


#去除问题中只有英文字母的行
eeee=df['questions'].apply(lambda x:None if str(x).encode('UTF-8').isalpha()==True else x)


# In[37]:


df['questions']=eeee


# In[38]:


df.dropna(inplace=True)


# In[39]:


#查看数据结构
df.shape


# In[40]:


#保存文件
df.to_csv(r'C:\Users\D\Desktop\新data.csv',index=None)


# In[ ]:





# In[41]:


#这里出来是questions的集合
def getquestions(data):
    listquestions=[]
    for questions in data['questions']:
        listquestions.append(questions)
    return listquestions


# In[42]:


#调用函数
questions=getquestions(df)


# In[43]:


#分词和去停用词
questionscut=wordcut(questions)


# In[46]:


print(questionscut)


# In[92]:


#写入文件
file=open('questionscut.txt','w',encoding='UTF-8')
for i in questionscut:
    file.write(" ".join(i))
    file.write('\n')
    file.close()


# In[81]:


#把列表合并成一个
    abc=[]
    for i in questionscut:
        abc+=i
print(abc)


# In[82]:


transformer = TfidfVectorizer()
X_train = transformer.fit_transform(abc)


# In[85]:


word_list = transformer.get_feature_names()#所有统计的词
print(transformer.fit(abc).vocabulary_)#统计词字典形式


# In[90]:


#TFIDF权重
weight_train = X_train.toarray()
tfidf_matrix = transformer.fit_transform(abc)
print(tfidf_matrix.toarray())


# In[ ]:




