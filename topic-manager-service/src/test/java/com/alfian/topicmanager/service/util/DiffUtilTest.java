package com.alfian.topicmanager.service.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiffUtilTest {


  @Test
  void sameListTest(){
    List<String> firstList = Arrays.asList("A","B","C","D");
    List<String> secondList = new ArrayList<>(firstList);

    List<DiffResult<String>> diffResults = DiffUtil.getDiff(firstList, secondList, String::compareTo);

    assertEquals(0, diffResults.size());
  }

  @Test
  void sameSizeListTest() {
    List<String> firstList = Arrays.asList( "A","B","C","D"    ,"F");
    List<String> secondList = Arrays.asList("A"    ,"C","D","E"    ,"G");

    List<DiffResult<String>> diffResults = DiffUtil.getDiff(firstList, secondList, String::compareTo);

    assertEquals(4, diffResults.size());
    assertEquals(2, (int) diffResults.stream().filter(DiffResult::isFirstListItem).count());
    assertEquals(2, (int) diffResults.stream().filter(diffResult -> !diffResult.isFirstListItem()).count());

  }

  @Test
  void diffListTest() {
    List<String> firstList = Arrays.asList( "A","B","C","D"    ,"F");
    List<String> secondList = Arrays.asList("A"    ,"C","D","E");

    List<DiffResult<String>> diffResults = DiffUtil.getDiff(firstList, secondList, String::compareTo);

    assertEquals(3, diffResults.size());
    assertEquals(2, (int) diffResults.stream().filter(DiffResult::isFirstListItem).count());
    assertEquals(1, (int) diffResults.stream().filter(diffResult -> !diffResult.isFirstListItem()).count());

  }

}
