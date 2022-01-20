#-*- coding: utf-8 -*-
import torch.nn as nn
import torch.nn.functional as F
import torch.optim as optim
import torch
import numpy as np 
import matplotlib.pyplot as plt
from matplotlib import ticker
import torchvision
from torchvision import datasets, transforms

#数据下载
transform = transforms.Compose([transforms.ToTensor(),
                                transforms.Lambda(lambda x: x.repeat(3,1,1)),
                                transforms.Normalize(mean=[0.5,0.5,0.5],std=[0.5,0.5,0.5])])
data_train = datasets.MNIST(root = "./data/",transform=transform,train = True,download = True)
data_test = datasets.MNIST(root="./data/",transform = transform,train = False)

#数据导入
data_loader_train = torch.utils.data.DataLoader(dataset=data_train,batch_size = 50,shuffle = True)
data_loader_test = torch.utils.data.DataLoader(dataset=data_test,batch_size = 50,shuffle = True)

#数据集图像预览
images, labels = next(iter(data_loader_train))
img = torchvision.utils.make_grid(images)
img = img.numpy().transpose(1,2,0)
std = [0.5,0.5,0.5]
mean = [0.5,0.5,0.5]
img = img*std+mean
print([labels[i] for i in range(50)])
plt.imshow(img)

train_losses = []
train_counter = []
train_losses1 = []
train_counter1 = []
train_losses2 = []
train_counter2 = []
train_losses3 = []
train_counter3 = []

test_losses = []
test_counter =[]
test_losses1 = []
test_counter1 =[]
test_losses2 = []
test_counter2 =[]
test_losses3 = []
test_counter3 =[]

train_accuracy = []
train_accuracy1 = []
train_accuracy2 = []
train_accuracy3 = []

test_accuracy = []
test_accuracy1 = []
test_accuracy2 = []
test_accuracy3 = []

#构建全连接层神经网络
class Net(nn.Module):
    def __init__(self):
        super(Net, self).__init__()

        self.fc1 = nn.Linear(3*28*28, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)
        
    def forward(self, x):
        
        x = x.view(-1, 3*28*28)
        m1 = nn.BatchNorm1d(120,0.9,affine=True) #BN层正则化
        m2 = nn.BatchNorm1d(84,0.9,affine=True)  #BN层正则化
        x = F.relu(self.fc1(x))     
#        x=m1(x)
#        x = F.dropout(x,p=0.1)                  #Dropout正则化
        x = F.relu(self.fc2(x))
#        x=m2(x)
#        x = F.dropout(x,p=0.1)                  #Dropout正则化
        x = self.fc3(x)
        return x

net = Net()

#使用分类交叉熵损失函数
criterion = nn.CrossEntropyLoss()

#随机梯度下降法
optimizer = optim.SGD(net.parameters(), lr=0.001, momentum=0.9)  #动量项

#训练20epochs
correct = 0
total = 0

for epoch in range(5):  # loop over the dataset multiple times
    train_loss0=0
    running_loss = 0.0
    for i, data in enumerate(data_loader_train,0):
        # get the inputs; data is a list of [inputs, labels]
        inputs, labels = data

        # zero the parameter gradients
        optimizer.zero_grad()

        # forward + backward + optimize
        outputs = net(inputs)
        
        _,predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()

        # print statistics
        running_loss += loss.item()
        train_loss0 += loss.item()
        if i % 200 == 199:    # print every 200 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (epoch + 1, i + 1, running_loss / 200))            
    
            running_loss = 0.0
    train_losses.append(train_loss0/1200)        
    print('Accuracy of the network on the 60000 train images: %.2f %%' % (100 * correct / total))
    train_accuracy.append( correct / total)
    print('Finished Training')

    #MNIST测试集测试当前神经网络
    correct = 0
    total = 0
    test_loss0=0
    with torch.no_grad():
        for i, data in enumerate(data_loader_test,0):
            images, labels = data
            outputs = net(images)
            
            loss = criterion(outputs, labels)
            running_loss += loss.item()
            test_loss0+= loss.item()
            
            _,predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
            
            if i % 40 == 39:    # print every 20 mini-batches
                print('[%d, %5d] loss: %.3f' %
                      (epoch + 1, i + 1, running_loss / 40))            
                
                running_loss = 0.0 
    test_losses.append(test_loss0/200)     
    print('Accuracy of the network on the 10000 test images: %.2f %%' % (100 * correct / total))
    test_accuracy.append( correct / total)


#构建全连接层神经网络不使用动量项
class Net1(nn.Module):
    def __init__(self):
        super(Net1, self).__init__()

        self.fc1 = nn.Linear(3*28*28, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)
        
    def forward(self, x):
        
        x = x.view(-1, 3*28*28)
        m1 = nn.BatchNorm1d(120,0.9,affine=True) #BN层正则化
        m2 = nn.BatchNorm1d(84,0.9,affine=True)  #BN层正则化
        x = F.relu(self.fc1(x))     
#        x=m1(x)
#        x = F.dropout(x,p=0.1)                  #Dropout正则化
        x = F.relu(self.fc2(x))
#        x=m2(x)
#        x = F.dropout(x,p=0.1)                  #Dropout正则化
        x = self.fc3(x)
        return x

net1 = Net1()

#使用分类交叉熵损失函数
criterion = nn.CrossEntropyLoss()

#随机梯度下降法
optimizer1 = optim.SGD(net1.parameters(), lr=0.001, momentum=0)  #动量项

#训练20epochs
correct = 0
total = 0

for epoch in range(20):  # loop over the dataset multiple times
    train_loss1=0
    running_loss = 0.0
    for i, data in enumerate(data_loader_train,0):
        # get the inputs; data is a list of [inputs, labels]
        inputs, labels = data

        # zero the parameter gradients
        optimizer1.zero_grad()

        # forward + backward + optimize
        outputs = net1(inputs)
        
        _,predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer1.step()

        # print statistics
        running_loss += loss.item()
        train_loss1 += loss.item()
        if i % 200 == 199:    # print every 200 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (epoch + 1, i + 1, running_loss / 200))            
    
            running_loss = 0.0
    train_losses1.append(train_loss1/1200)        
    print('Accuracy of the network on the 60000 train images: %.2f %%' % (100 * correct / total))
    train_accuracy1.append( correct / total)
    print('Finished Training')

    #MNIST测试集测试当前神经网络
    correct = 0
    total = 0
    test_loss1=0
    with torch.no_grad():
        for i, data in enumerate(data_loader_test,0):
            images, labels = data
            outputs = net1(images)
            
            loss = criterion(outputs, labels)
            running_loss += loss.item()
            test_loss1+= loss.item()
            
            _,predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
            
            if i % 40 == 39:    # print every 20 mini-batches
                print('[%d, %5d] loss: %.3f' %
                      (epoch + 1, i + 1, running_loss / 40))            
                
                running_loss = 0.0 
    test_losses1.append(test_loss1/200)     
    print('Accuracy of the network on the 10000 test images: %.2f %%' % (100 * correct / total))
    test_accuracy1.append( correct / total)


#构建全连接层神经网络使用Dropout
class Net2(nn.Module):
    def __init__(self):
        super(Net2, self).__init__()

        self.fc1 = nn.Linear(3*28*28, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)
        
    def forward(self, x):
        
        x = x.view(-1, 3*28*28)
        m1 = nn.BatchNorm1d(120,0.9,affine=True) #BN层正则化
        m2 = nn.BatchNorm1d(84,0.9,affine=True)  #BN层正则化
        x = F.relu(self.fc1(x))     
#        x=m1(x)
        x = F.dropout(x,p=0.2,training=self.training)                  #Dropout正则化
        x = F.relu(self.fc2(x))
#        x=m2(x)
        x = F.dropout(x,p=0.2,training=self.training)                  #Dropout正则化
        x = self.fc3(x)
        return x

net2 = Net2()

#使用分类交叉熵损失函数
criterion = nn.CrossEntropyLoss()

#随机梯度下降法
optimizer = optim.SGD(net2.parameters(), lr=0.001, momentum=0.9)  #动量项

#训练20epochs
correct = 0
total = 0

for epoch in range(5):  # loop over the dataset multiple times
    train_loss2=0
    running_loss = 0.0
    for i, data in enumerate(data_loader_train,0):
        # get the inputs; data is a list of [inputs, labels]
        inputs, labels = data

        # zero the parameter gradients
        optimizer.zero_grad()

        # forward + backward + optimize
        outputs = net2(inputs)
        
        _,predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()

        # print statistics
        running_loss += loss.item()
        train_loss2 += loss.item()
        if i % 200 == 199:    # print every 200 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (epoch + 1, i + 1, running_loss / 200))            
    
            running_loss = 0.0
    train_losses2.append(train_loss2/1200)        
    print('Accuracy of the network on the 60000 train images: %.2f %%' % (100 * correct / total))
    train_accuracy2.append( correct / total)
    print('Finished Training')

    #MNIST测试集测试当前神经网络
    correct = 0
    total = 0
    test_loss2=0
    with torch.no_grad():
        for i, data in enumerate(data_loader_test,0):
            images, labels = data
            outputs = net2(images)
            
            loss = criterion(outputs, labels)
            running_loss += loss.item()
            test_loss2+= loss.item()
            
            _,predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
            
            if i % 40 == 39:    # print every 20 mini-batches
                print('[%d, %5d] loss: %.3f' %
                      (epoch + 1, i + 1, running_loss / 40))            
                
                running_loss = 0.0 
    test_losses2.append(test_loss2/200)     
    print('Accuracy of the network on the 10000 test images: %.2f %%' % (100 * correct / total))
    test_accuracy2.append( correct / total)


#构建全连接层神经网络使用BN
class Net3(nn.Module):
    def __init__(self):
        super(Net3, self).__init__()

        self.fc1 = nn.Linear(3*28*28, 120)
        self.fc2 = nn.Linear(120, 84)
        self.fc3 = nn.Linear(84, 10)
        
    def forward(self, x):
        
        x = x.view(-1, 3*28*28)
        m1 = nn.BatchNorm1d(120,0.9,affine=True) #BN层正则化
        m2 = nn.BatchNorm1d(84,0.9,affine=True)  #BN层正则化
        x = F.relu(self.fc1(x))     
        x=m1(x)
#        x = F.dropout(x,p=0.2)                  #Dropout正则化
        x = F.relu(self.fc2(x))
        x=m2(x)
#        x = F.dropout(x,p=0.2)                  #Dropout正则化
        x = self.fc3(x)
        return x

net3 = Net3()

#使用分类交叉熵损失函数
criterion = nn.CrossEntropyLoss()

#随机梯度下降法
optimizer = optim.SGD(net3.parameters(), lr=0.001, momentum=0.9)  #动量项

#训练20epochs
correct = 0
total = 0

for epoch in range(20):  # loop over the dataset multiple times
    train_loss3=0
    running_loss = 0.0
    for i, data in enumerate(data_loader_train,0):
        # get the inputs; data is a list of [inputs, labels]
        inputs, labels = data

        # zero the parameter gradients
        optimizer.zero_grad()

        # forward + backward + optimize
        outputs = net3(inputs)
        
        _,predicted = torch.max(outputs.data, 1)
        total += labels.size(0)
        correct += (predicted == labels).sum().item()
        
        loss = criterion(outputs, labels)
        loss.backward()
        optimizer.step()

        # print statistics
        running_loss += loss.item()
        train_loss3 += loss.item()
        if i % 200 == 199:    # print every 200 mini-batches
            print('[%d, %5d] loss: %.3f' %
                  (epoch + 1, i + 1, running_loss / 200))            
    
            running_loss = 0.0
    train_losses3.append(train_loss3/1200)        
    print('Accuracy of the network on the 60000 train images: %.2f %%' % (100 * correct / total))
    train_accuracy3.append( correct / total)
    print('Finished Training')

    #MNIST测试集测试当前神经网络
    correct = 0
    total = 0
    test_loss3=0
    with torch.no_grad():
        for i, data in enumerate(data_loader_test,0):
            images, labels = data
            outputs = net3(images)
            
            loss = criterion(outputs, labels)
            running_loss += loss.item()
            test_loss3+= loss.item()
            
            _,predicted = torch.max(outputs.data, 1)
            total += labels.size(0)
            correct += (predicted == labels).sum().item()
            
            if i % 40 == 39:    # print every 20 mini-batches
                print('[%d, %5d] loss: %.3f' %
                      (epoch + 1, i + 1, running_loss / 40))            
                
                running_loss = 0.0 
    test_losses3.append(test_loss3/200)     
    print('Accuracy of the network on the 10000 test images: %.2f %%' % (100 * correct / total))
    test_accuracy3.append( correct / total)

#测试结果图对比

#(1)
train_counter=list(range(1,len(train_losses)+1))
train_counter1=list(range(1,len(train_losses1)+1))
plt.plot(train_counter, train_losses, color='blue')
plt.plot(train_counter1, train_losses1, color='red')
plt.legend(['loss with momentum', 'loss without momentum'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Training Loss')
plt.show()

train_counter=list(range(1,len(train_accuracy)+1))
train_counter1=list(range(1,len(train_accuracy1)+1))
plt.plot(train_counter, train_accuracy, color='blue')
plt.plot(train_counter1, train_accuracy1, color='red')
plt.legend(['accuracy with momentum', 'accuracy without momentum'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Training accuracy')
plt.show()

test_counter=list(range(1,len(test_losses)+1))
test_counter1=list(range(1,len(test_losses1)+1))
plt.plot(test_counter, test_losses, color='blue')
plt.plot(test_counter1, test_losses1, color='red')
plt.legend(['loss with momentum', 'loss without momentum'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Loss of tests')
plt.show()

test_counter=list(range(1,len(test_accuracy)+1))
test_counter1=list(range(1,len(test_accuracy1)+1))
plt.plot(test_counter, test_accuracy, color='blue')
plt.plot(test_counter1, test_accuracy1, color='red')
plt.legend(['accuracy with momentum', 'accuracy without momentum'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy of tests')
plt.show()

#(2)

train_counter=list(range(1,len(train_losses)+1))
train_counter2=list(range(1,len(train_losses2)+1))
plt.plot(train_counter, train_losses, color='blue')
plt.plot(train_counter2, train_losses2, color='red')
plt.legend(['loss without Dropout', 'loss with Dropout'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Training Loss')
plt.show()

train_counter=list(range(1,len(train_accuracy)+1))
train_counter2=list(range(1,len(train_accuracy2)+1))
plt.plot(train_counter, train_accuracy, color='blue')
plt.plot(train_counter2, train_accuracy2, color='red')
plt.legend(['accuracy without Dropout', 'accuracy with Dropout'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Training accuracy')
plt.show()

test_counter=list(range(1,len(test_losses)+1))
test_counter2=list(range(1,len(test_losses2)+1))
plt.plot(test_counter, test_losses, color='blue')
plt.plot(test_counter2, test_losses2, color='red')
plt.legend(['loss without Dropout', 'loss with Dropout'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Testing loss')
plt.show()

test_counter=list(range(1,len(test_accuracy)+1))
test_counter2=list(range(1,len(test_accuracy2)+1))
plt.plot(test_counter, test_accuracy, color='blue')
plt.plot(test_counter2, test_accuracy2, color='red')
plt.legend(['accuracy without Dropout', 'accuracy with Dropout'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Testing accuracy')
plt.show()

train_counter=list(range(1,len(train_accuracy)+1))
test_counter=list(range(1,len(test_accuracy)+1))
plt.plot(train_counter, train_accuracy, color='blue')
plt.plot(test_counter, test_accuracy, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy without Dropout')
plt.show()

train_counter2=list(range(1,len(train_accuracy2)+1))
test_counter2=list(range(1,len(test_accuracy2)+1))
plt.plot(train_counter2, train_accuracy2, color='blue')
plt.plot(test_counter2, test_accuracy2, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy with Dropout')
plt.show()

#(3)

train_counter=list(range(1,len(train_losses)+1))
train_counter3=list(range(1,len(train_losses3)+1))
plt.plot(train_counter, train_losses, color='blue')
plt.plot(train_counter3, train_losses3, color='red')
plt.legend(['loss without BN', 'loss with BN'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Training Loss')
plt.show()

train_counter=list(range(1,len(train_accuracy)+1))
train_counter3=list(range(1,len(train_accuracy3)+1))
plt.plot(train_counter, train_accuracy, color='blue')
plt.plot(train_counter3, train_accuracy3, color='red')
plt.legend(['accuracy without BN', 'accuracy with BN'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Training accuracy')
plt.show()

test_counter=list(range(1,len(test_losses)+1))
test_counter3=list(range(1,len(test_losses3)+1))
plt.plot(test_counter, test_losses, color='blue')
plt.plot(test_counter3, test_losses3, color='red')
plt.legend(['loss without BN', 'loss with BN'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Testing loss')
plt.show()

test_counter=list(range(1,len(test_accuracy)+1))
test_counter3=list(range(1,len(test_accuracy3)+1))
plt.plot(test_counter, test_accuracy, color='blue')
plt.plot(test_counter3, test_accuracy3, color='red')
plt.legend(['accuracy without BN', 'accuracy with BN'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Testing accuracy')
plt.show()

train_counter=list(range(1,len(train_accuracy)+1))
test_counter=list(range(1,len(test_accuracy)+1))
plt.plot(train_counter, train_accuracy, color='blue')
plt.plot(test_counter, test_accuracy, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy without BN')
plt.show()

train_counter3=list(range(1,len(train_accuracy3)+1))
test_counter3=list(range(1,len(test_accuracy3)+1))
plt.plot(train_counter3, train_accuracy3, color='blue')
plt.plot(test_counter3, test_accuracy3, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy with BN')
plt.show()

#(4)

train_counter2=list(range(1,len(train_losses2)+1))
train_counter3=list(range(1,len(train_losses3)+1))
plt.plot(train_counter2, train_losses2, color='blue')
plt.plot(train_counter3, train_losses3, color='red')
plt.legend(['loss with Dropout', 'loss with BN'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Training Loss')
plt.show()

train_counter2=list(range(1,len(train_accuracy2)+1))
train_counter3=list(range(1,len(train_accuracy3)+1))
plt.plot(train_counter2, train_accuracy2, color='blue')
plt.plot(train_counter3, train_accuracy3, color='red')
plt.legend(['accuracy with Dropout', 'accuracy with BN'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Training accuracy')
plt.show()

test_counter2=list(range(1,len(test_losses2)+1))
test_counter3=list(range(1,len(test_losses3)+1))
plt.plot(test_counter2, test_losses2, color='blue')
plt.plot(test_counter3, test_losses3, color='red')
plt.legend(['loss with Dropout', 'loss with BN'], loc='upper right')
plt.xlabel('epoches')
plt.ylabel('loss')
plt.title('Testing loss')
plt.show()

test_counter2=list(range(1,len(test_accuracy2)+1))
test_counter3=list(range(1,len(test_accuracy3)+1))
plt.plot(test_counter2, test_accuracy2, color='blue')
plt.plot(test_counter3, test_accuracy3, color='red')
plt.legend(['accuracy with Dropout', 'accuracy with BN'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Testing accuracy')
plt.show()

train_counter2=list(range(1,len(train_accuracy2)+1))
test_counter2=list(range(1,len(test_accuracy2)+1))
plt.plot(train_counter2, train_accuracy2, color='blue')
plt.plot(test_counter2, test_accuracy2, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy with Dropout')
plt.show()

train_counter3=list(range(1,len(train_accuracy3)+1))
test_counter3=list(range(1,len(test_accuracy3)+1))
plt.plot(train_counter3, train_accuracy3, color='blue')
plt.plot(test_counter3, test_accuracy3, color='red')
plt.legend(['training accuracy', 'testing accuracy'], loc='lower right')
plt.xlabel('epoches')
plt.ylabel('accuracy')
plt.title('Accuracy with BN')
plt.show()

#保存当前神经网络
PATH = './cifar_net.pth'
torch.save(net.state_dict(), PATH)