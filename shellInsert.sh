#!/bin/sh
#when using "cd path", instead alias to shellInsert so we get shellInsert path
#alias cd='. ./shellFind.sh'
#alias jp='. ./shellInsert.sh'
#Insert does the actual `cd-ing` all the cds in find call on insert
#Insert avoids hashing .. or . because those don't always map to the same directory
#Insert determines if the argument is an existing directory. If yes, then cd to
#it and hash it. If not, then try to cd to it. Insert is also used in find

Insert(){
    myjpInsertKey=$1
    myjpclasspath="/home/user/cdProject/hashCode/dict"
    #if directory exists, add it to hash
    if [ "$myjpInsertKey" == ".." -o "$myjpInsertKey" == "." ];
    then
	builtin cd $myjpInsertKey
    else
	if [ -d $myjpInsertKey ] 
	then
	    builtin cd $myjpInsertKey
	    myjpInsertVal=`pwd`
	    java -classpath $myjpclasspath JPInsert $myjpInsertKey $myjpInsertVal
	else
	    builtin cd $myjpInsertKey
	fi
    fi
}

Insert $1