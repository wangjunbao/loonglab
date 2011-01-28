PWD=`pwd`
for i in ../lib/*.jar;
do classpath=$PWD/$i:"$classpath";
done

#echo $classpath

classpath=/home/nlp/classes:$classpath

   echo "wait for connect database..."
   java -cp $classpath -Xms64m -Xmx256m com.example.MyTask $1 &