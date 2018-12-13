LocalFileName=$1
DataDir=$2
rm $DataDir/$LocalFileName
substring=$(echo $LocalFileName | cut -d. -f1)
cat $DataDir/$substring-*-* > $DataDir/$LocalFileName
rm $DataDir/$substring-*-*
