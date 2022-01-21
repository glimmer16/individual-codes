# -*- coding: utf-8 -*-
import requests
from bs4 import BeautifulSoup
import re
from selenium import webdriver
import time
from lxml import etree

def get_links():
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'zhannei.baidu.com'}  #请求头
    links = []
    for i in range(0, 4):
        link = 'http://zhannei.baidu.com/cse/search?q=%E6%96%B0%E5%86%A0%E7%96%AB%E6%83%85&p='+str(i)+'&s=6006151520371175686' #构造请求url
        r = requests.get(link, headers=headers)  #请求体，发送网络请求，请求方式为GET
        r.encoding='utf-8'
        soup = BeautifulSoup(r.text, 'lxml')   #数据解析
        div_list = soup.find_all('h3', class_='c-title')    #获取网址
        for each in div_list:
            patt = r'<a cpos=\"title\" href=\"(.*?)\" rpos=\"\" target=\"_blank\">'
            m = re.findall(patt, ''.join('%s' %id for id in each),re.M)
            links.append(m)
              
    return links


def get_titles(links):
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'news.youth.cn'}  #请求头
    r = requests.get(links, headers=headers)  #请求体，发送网络请求，请求方式为GET
    r.encoding='GB2312'
    soup = BeautifulSoup(r.text, 'lxml')   #数据解析  
    titles = re.findall('<h1>(.*?)</h1>',r.text,re.M)      #获取标题
    t=soup.find('div', class_='page_bt')
    if len(titles)==0 and t!=None:          #考虑不同类型的新闻
        patt='<p class=\"pbt\">(.*?)</p>'
        titles = re.findall(patt, ''.join('%s' %id for id in t),re.M)
    return titles 

def get_dates(links):
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'news.youth.cn'}  #请求头
    r = requests.get(links, headers=headers)  #请求体，发送网络请求，请求方式为GET
    r.encoding='GB2312'
    soup = BeautifulSoup(r.text, 'lxml')   #数据解析
    div_list = soup.find('span', id='page_right')   
    date='无'
    if div_list!=None :
        date = div_list.text.strip()
    return date                      

def get_sources(links):
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'news.youth.cn'}  #请求头
    r = requests.get(links, headers=headers)  #请求体，发送网络请求，请求方式为GET
    r.encoding='GB2312'
    soup = BeautifulSoup(r.text, 'lxml')   #数据解析
    div_list = soup.find('span', id='source_baidu')   
    source='无'
    if div_list!=None:
        source = div_list.text.strip()
    return source

def get_texts(links):
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'news.youth.cn'}  #请求头
    r = requests.get(links, headers=headers)  #请求体，发送网络请求，请求方式为GET
    r.encoding='GB2312'
    soup = BeautifulSoup(r.text, 'lxml')   #数据解析
    div_list = soup.find('div', class_='TRS_Editor')   
    texts='无'
    if div_list!=None:
        texts = div_list.text.strip()
    return texts

def get_pictures(links):
    headers = {
        'user-agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36',
        'Host': 'news.youth.cn'}  #请求头
    r = requests.get(links, headers=headers)  #请求体，发送网络请求，请求方式为GET
    r.encoding='GB2312'
    soup = BeautifulSoup(r.text, 'lxml')   #数据解析
    div_list = soup.find('p', align='center')   
    patt = r'oldsrc=\"(.*?)\" '
    #print(each.text)
    picture='无'
    if div_list!=None:
        picture = re.findall(patt, ''.join('%s' %id for id in div_list),re.M)    
    return picture


if __name__ == "__main__":
        
    driver = webdriver.Chrome(executable_path=r'C:\Users\dell\AppData\Local\Google\Chrome\Application\chromedriver.exe')#使用谷歌驱动
    driver.get("http://news.youth.cn/")     #使用Selenium模拟搜索    
    time.sleep(2)   #由于有可能存在网络加载慢等原因，所以这里先加载时暂停2秒之后再去获取表单元素
    kw=driver.find_element_by_id("q")   #定位到搜索框
    kw.send_keys("新冠疫情")    #向定位到的input填入值
    su=driver.find_element_by_partial_link_text("搜索")   #定位提交按钮
    su.click()  #模拟点击提交
    time.sleep(2)
    content = driver.page_source  # HTML内容
    contentx = etree.HTML(content)  # lxml解析
    #print(etree.tostring(contentx))
    url=contentx.xpath('//ul[@class="xq-list-wrap"]/li/div[@class="pic"]/a/@href')
    
    links=get_links()           #获取中国青年网搜索“新冠疫情”后前40条新闻链接
    for i in range(0,40):    
        s=''.join('%s' %id for id in links[i])
        
        title = get_titles(s)
        date=get_dates(s).replace("发稿时间：","")
        source=get_sources(s).replace("来源：","")
        text=get_texts(s)
        picture=get_pictures(s)
        
        with open('output_titles.csv', 'a', encoding='utf-8') as infofile:
            infofile.write(str(title) + '\n')
            infofile.flush()
            infofile.close()  #数据存储，存储方式为csv
        with open('output_dates.csv', 'a', encoding='utf-8') as infofile:
            infofile.write(str(date) + '\n')
            infofile.flush()
            infofile.close()  #数据存储，存储方式为csv
        with open('output_sources.csv', 'a', encoding='utf-8') as infofile:
            infofile.write(str(source) + '\n')
            infofile.flush()
            infofile.close()  #数据存储，存储方式为csv
        with open('output_texts.csv', 'a', encoding='utf-8') as infofile:
            infofile.write(str(text) + '\n')
            infofile.flush()
            infofile.close()  #数据存储，存储方式为csv
        with open('output_pictures.csv', 'a', encoding='utf-8') as infofile:
            infofile.write(str(picture) + '\n')
            infofile.flush()
            infofile.close()  #数据存储，存储方式为csv
    