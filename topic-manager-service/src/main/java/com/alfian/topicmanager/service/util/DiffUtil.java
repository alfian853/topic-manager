package com.alfian.topicmanager.service.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DiffUtil {

  public static <T> T getItemSafelyFromList(int index, List<T> list){
    if(index >= list.size())return null;
    return list.get(index);
  }

  public static <T> List<DiffResult<T>> getDiff(List<T> firstList, List<T> secondList, Comparator<T> comparator){
    firstList.sort(comparator);
    secondList.sort(comparator);

    int fi = 0;//current data index
    int si = 0;//request data index

    int fl = firstList.size();
    int sl = secondList.size();

    List<DiffResult<T>> diffResults = new ArrayList<>();

    while ((fi < fl) || (si < sl)){
      T fItem = getItemSafelyFromList(fi, firstList);
      T sItem = getItemSafelyFromList(si, secondList);

      int compareResult;

      if(fItem == null || sItem == null)
        compareResult = (fItem == null) ? 1 : -1;
      else{
        compareResult = comparator.compare(fItem, sItem);
      }

      if(compareResult == 0){
        fi++;
        si++;
      }
      else if(compareResult < 0){
        fi++;
        diffResults.add(new DiffResult<>(fItem, true));
        if(fi >= fl && si+1 < sl-1){
          diffResults.addAll(
            secondList.subList(si+1,sl-1)
              .stream().map(item -> new DiffResult<>(item, false))
              .collect(Collectors.toList())
          );
          break;
        }
      }
      else{
        si++;
        diffResults.add(new DiffResult<>(sItem, false));
        if(si >= sl && fi+1 < fl-1){
          diffResults.addAll(
            firstList.subList(fi+1,fl-1)
              .stream().map(item -> new DiffResult<>(item, true))
              .collect(Collectors.toList())
          );
          break;
        }
      }
    }

    return diffResults;
  }



}
