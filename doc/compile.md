
# 概述
compile模块的目标是通过antlr工具来解析mysql的sql脚本，并生成AST。通过AST以及提供的方言
映射成其他数据库的脚本，如pg，ms，oracle。

与Druid的sqlparser做对比。

# 词法分析
## 有限自动机  正则文法
Lex
GNU 版本，Flex



# java生态链的解析器
JSqlParser

# 语法分析
acc（或 GNU 的版本，Bison）、Antlr、JavaCC

参考：https://github.com/antlr/grammars-v4/blob/master/sql/mysql/Positive-Technologies/MySqlLexer.g4
https://resources.jointjs.com/demos/javascript-ast