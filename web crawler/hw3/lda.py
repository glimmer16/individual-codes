import numpy as np
import pandas as pd
import re
from gensim import corpora, models, similarities
import gensim

df = pd.read_csv("data_result.txt", error_bad_lines=False)

docslist = df.values
texts = [[word for word in str(doc).replace('[', '').replace(']', '').replace('\'', '').split()] for doc in docslist]

dictionary = corpora.Dictionary(texts)
corpus = [dictionary.doc2bow(text) for text in texts]

lda = gensim.models.ldamodel.LdaModel(corpus=corpus, id2word=dictionary, num_topics=20)
print(lda.print_topic(10, topn=5))

print('end')