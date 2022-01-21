import numpy as np
import gensim
from sklearn.cluster import KMeans
import matplotlib
import matplotlib.pyplot as pl

model = gensim.models.word2vec.Word2Vec.load('data.model')

if __name__ == "__main__":
    f = open('data_result.txt', 'r', encoding='utf-8')
    w = open('result.txt', 'w', encoding='utf-8')

    keys = model.wv
    wordvector = []
    for key in keys.vectors:
        wordvector.append(key)

    # 分类
    clf = KMeans(n_clusters=10)
    s = clf.fit(wordvector)
    print(s)
    # 获取到所有词向量所属类别
    labels = clf.labels_
    # 获取聚类中心
    cent = clf.cluster_centers_
    centroids = []
    for i in range(0, 10):
        min = 99999
        flag = 0
        for j in range(0, len(wordvector)):
            if min > np.linalg.norm(cent[i] - wordvector[j]):
                min = np.linalg.norm(cent[i] - wordvector[j])
                flag = j
        centroids.append(keys.index_to_key[flag])

    class_distance_count = [0]*10
    for i in range(0, len(wordvector)):
        temp = np.linalg.norm(cent[labels[i]] - wordvector[i])
        class_distance_count[labels[i]] = class_distance_count[labels[i]] + temp
    with open('output.txt', 'w') as f:
        for i in centroids:
            f.write(str(i))
            f.write('\t')

    classCollects = {}
    for i in range(len(keys)):
        if labels[i] in classCollects.keys():
            classCollects[labels[i]].append(keys[i])
        else:
            classCollects[labels[i]] = [keys[i]]

    x = []
    y = []
    for i in range(0, 9):
        x.append(centroids[i])
        y.append(len(classCollects[i])/class_distance_count[i])
    matplotlib.rcParams['font.family'] = 'SimHei'
    fig, ax = pl.subplots()
    ax.bar(x, y, width=0.5)
    ax.set_xlabel("关键词") 
    ax.set_ylabel("聚类指标") 
    ax.set_title("用户关注点")
    pl.show()  
    print()
