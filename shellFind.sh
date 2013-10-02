#!/bin/sh
#alias jp='. ./shellInsert.sh'
#alias cd='. ./shellFind.sh'
#Find acts similarly to cd. It first tries jp-Insert cd. If not, then 
#it will try to find the directory from a hash
#if the directory is an existing directory, then jpInsert-cd to it. If not, 
#then it might be a deleted entry. Remove this from the hash so we won't 
#go back to it again. This directory might actually exist, though (it's just
#not hashed yet). If so, try going to it with jpInsert-cd. 
#in find, the unhashed path gets priority. First check to see if the key's path exists,
#then, find it from hash as last choice
Find(){
    myjpFindKey=$1
    myjpFindOption=$2
    myjpclasspath="/home/user/cdProject/hashCode/dict"
    myjpFindPath=`java -classpath $myjpclasspath JPFind $myjpFindKey $myjpFindOption`
    if [ "$myjpFindOption" == "print" ]
    then
	echo $myjpFindPath
    else
	if [ -d $myjpFindKey ]
	then
	    jp $myjpFindKey
	else
	    #if I can't cd to the key directly, then find a path from the hash
	    if [ -d $myjpFindPath ]
	    then
		jp $myjpFindPath 
	    else
		java -classpath $myjpclasspath JPRemove $myjpFindPath
	        #if myjpfindpath is invalid, remove it and try a normal cd anyway
		jp $myjpFindKey
	    fi
	fi
    fi
}

Find $@