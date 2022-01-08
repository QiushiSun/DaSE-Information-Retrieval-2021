# Dictionary & Tolerant Retrieval

## 索引词典可以采用什么样的数据结构来组织

### 用于词项定位的数据结构（Hash Table或树）

采用哈希表或树的准则:

1. 词项数目是否固定或者说词项数目是否持续增长？
2. 词项的相对访问频率如何？
3. 词项的数目有多少？

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/949f5fe1-038a-48d7-b5e5-b831eab82cea/Untitled.png)

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4041f042-da0c-44c5-9efe-014f677c40d2/Untitled.png)

## 通配查询的处理

例子：m*nchen

1. 在B-树中分别查找满足m*和*nchen的词项集合，然后求交集，开销很大
2. 轮排(permuterm) 索引

### 轮排索引

构建基本思想：

首先我们在字符集中引入一个新的符号$，用于标识词项开始和结束。

枚举一个词项中所有连读的k个字符构成的k‐gram ，将所有的k-gram建立倒排索引。即对词典中的词项词汇表再进行了一层索引

## 拼写纠正

### 词独立法

只检查每个单词本身的拼写错误，如果某个单词拼写错误后变成另外一个单词，则无法查

### Edit distance

### Levenshtein distance

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/e92f06c0-9c81-4a0a-a123-e1bdd1dff77b/Untitled.png)

### 上下文敏感纠正（语境相关纠正）

纠错时要考虑周围的单词

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/4451a37e-7c87-42b0-b3f8-8acee104fe55/Untitled.png)

## Soundex

Soundex是寻找发音相似的单词的方法，比如: chebyshev / tchebyscheff

### Soundex算法:

1. 将词典中每个词项转换成一个4字符缩减形式
2. 对查询词项做同样的处理
3. 基于4-字符缩减形式进行索引和搜索

![Untitled](https://s3-us-west-2.amazonaws.com/secure.notion-static.com/91ba2e43-cfd0-4452-af66-4ff40ca535ee/Untitled.png)

例子: 采用Soundex算法处理HERMAN

1. 保留H
2. ERMAN → 0RM0N
3. 0RM0N → 06505
4. 06505 → 06505
5. 06505 → 655
6. 返回H655

注意: HERMANN 会产生同样的编码

### Soundex的应用情况

在IR中并不非常普遍，适用于“高召回率”任务(e.g., 国际刑警组织Interpol在全球范围内追查罪犯)