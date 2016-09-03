# Simple Checkcode Recognition

之前在博客中写了一篇有关简单验证码识别的文章，收到很多小伙伴的来信，希望分享下代码，现在将此代码进行规整后共享，希望能够帮助感兴趣的朋友，博客文章地址:
[http://www.codeboy.me/2014/10/03/datamine-svm/](http://www.codeboy.me/2014/10/03/datamine-svm/)

### 环境配置

- IntelliJ IDEA (集成开发环境)
- Jdk (java)
- Gradle (项目管理工具，也可以maven，需要自行进行部分转换)

### 简单说明

- 验证码的识别具有一定的局限性，不同的验证码需要经过的处理不相同，本项目针对固定的验证码进行训练和识别。
- 简单的验证码比较容易识别，复杂一点很难有很高的识别率。
- 项目中使用libsvm进行模型的建立，并没有进行参数的调整。
- 有关分类相关的知识可以从周志华老师的[机器学习](https://list.tmall.com/search_product.htm?q=%BB%FA%C6%F7%D1%A7%CF%B0&type=p&spm=a220m.1000858.a2227oh.d100&from=.list.pc_1_searchbutton)中学习。



> 有任何问题何以发送邮件到app@codeboy.me进行交流。