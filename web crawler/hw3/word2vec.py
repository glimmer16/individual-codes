
import warnings
warnings.filterwarnings(action = 'ignore', category = UserWarning, module = 'gensim')#���Ծ���

from gensim.models import Word2Vec
from gensim.models.word2vec import LineSentence


inp = 'data_result.txt'
outp1 = 'data.model'
outp2 = 'data.vector'

model = Word2Vec(LineSentence(inp), window=5, vector_size=100, min_count=5, sg=1, hs=1, workers=25)
print(model)
model.save(outp1)
model.wv.save_word2vec_format(outp2, binary=False)
