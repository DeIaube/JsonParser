# JsonParser

## 思路

**将Json中的字符抽象为Token**

 - END_DOCUMENT：JSON文档结束
 
 - BEGIN_OBJECT：开始一个JSON object
 
 - END_OBJECT：结束一个JSON object
 
 - BEGIN_ARRAY：开始一个JSON array
 
 - END_ARRAY：结束一个JSON array
 
 - SEP_COLON：读取一个冒号
 
 - SEP_COMMA：读取一个逗号
 
 - STRING：一个String
 
 - BOOLEAN：一个true或false
 
 - NUMBER：一个number
 
 - NULL：一个null
 
 **将Reader最终封装为TokenReader,接口如下:**
 
 - Token readNextToken()：读取下一个Token
 
 - boolean readBoolean()：读取一个boolean
 
 - Number readNumber()：读取一个number
 
 - String readString()：读取一个string
 
 - void readNull()：读取一个null
 
 **在读取过程中，可以根据期望数据解析文本**
 
 - {：期待一个JSON object
 
 - :：期待一个JSON object的value
 
 - ,：期待一个JSON object的下一组key-value，或者一个JSON array的下一个元素
 
 - [：期待一个JSON array
 
 - t：期待一个true
 
 - f：期待一个false
 
 - n：期待一个null
 
 - "：期待一个string
 
 - 0~9：期待一个number
 
 **parse()将TokenReader中不断读取Token,根据当前状态设定下一个期望的状态,如与期望不符,则Json数据无效。**
 
**参考:[如何编写一个JSON解析器](http://www.liaoxuefeng.com/article/0014211269349633dda29ee3f29413c91fa65c372585f23000)**
