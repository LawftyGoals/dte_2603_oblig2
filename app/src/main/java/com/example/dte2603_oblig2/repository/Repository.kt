package com.example.dte2603_oblig2.repository

import com.example.dte2603_oblig2.data.CheckList
import com.example.dte2603_oblig2.data.CheckListSamples.CheckLists

class Repository {

    fun getAllCheckLists(): MutableList<CheckList> {
        return CheckLists
    }

    fun getCheckList(name: String): CheckList? {
        var targetChecklist: CheckList? = null
        for (checkList in CheckLists) {
            if (checkList.name == name) {
                targetChecklist = checkList
            }
        }

        return targetChecklist

    }

    fun addCheckList(checkList: CheckList): MutableList<CheckList> {
        CheckLists.add(checkList)

        return CheckLists

    }

    fun removeCheckList(checkList: CheckList): MutableList<CheckList> {
        CheckLists.remove(checkList)

        return CheckLists
    }

    fun editCheckList(prevCheckList: CheckList, changedCheckList: CheckList):
            MutableList<CheckList> {
        val targetCheckList = getCheckList(prevCheckList.name)

        if (targetCheckList != null) {
            targetCheckList.name = changedCheckList.name
            targetCheckList.icon = changedCheckList.icon
            targetCheckList.checkListItems = changedCheckList.checkListItems
        }

        return CheckLists

    }


}