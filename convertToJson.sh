echo $1
sed 's/^/  /g' $1 | tac | sed -e '1 s/,$//' -e '1i ]' | tac | sed '1i [' > "New_$1" 