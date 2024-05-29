#!/usr/bin/env bash


input='help'
while [ $input != 'exit' ]
do
  if [ $input = 'categories' ]
  then
    echo $(curl localhost:8080/categories)
    echo Выберите id необходимой категории или back
    read input
    if [ $input = 'back' ]
    then
      continue
    fi
    echo $(curl localhost:8080/articles/category/$input)
    echo Выберите id необходимой статьи или back
    read input
    if [ $input = 'back' ]
    then
      continue
    fi
    echo $(curl localhost:8080/articles/$input)
  elif [ $input = 'subjects' ]
  then
    echo $(curl localhost:8080/subjects)
    echo Выберите id необходимого предмета или back
    read input
    if [ $input = 'back' ]
    then
      continue
    fi
    echo $(curl localhost:8080/articles/subject/$input)
    echo Выберите id необходимой статьи или back
    read input
    if [ $input = 'back' ]
    then
      continue
    fi
    echo $(curl localhost:8080/articles/$input)
  elif [ $input = 'random' ]
  then
    echo $(curl localhost:8080/articles/random)
  elif [ $input = 'help' ]
  then
    echo categories - выбрать статью по категории
    echo subjects - выбрать статью по предмету
    echo random - выбрать случайную статью
    echo help - вывести этот текст
    echo exit - выход
  elif [ $input = 'back' ]
  then
    echo назад
  else
    echo неизвестная команда
  fi
  read input
done
