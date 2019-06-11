java -cp target/verifier-0.0.1-SNAPSHOT.jar com.epam.sudoku.verifier.Verifier $1
result=$?
echo ${result}
exit ${result}
