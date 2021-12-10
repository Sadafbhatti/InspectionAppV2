package com.example.inspectionapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Objects;

public class Model {
    String Count;
    String Message;
    String SearchCriteria;
    ArrayList<Results> Results=new ArrayList<>(300);

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    public String getSearchCriteria() {
        return SearchCriteria;
    }

    public void setSearchCriteria(String searchCriteria) {
        SearchCriteria = searchCriteria;
    }

    public ArrayList<Model.Results> getResults() {
        return Results;
    }

    public void setResults(ArrayList<Model.Results> results) {
        Results = results;
    }

    @Override
    public String toString() {
        return "Model{" +
                "Count='" + Count + '\'' +
                ", Message='" + Message + '\'' +
                ", SearchCriteria='" + SearchCriteria + '\'' +
                ", Results=" + Results +
                '}';
    }

    public class Results{
        String Value;
        String ValueId;
        String Variable;
        String VariableId;

        public String getValue() {
            return Value;
        }

        public void setValue(String value) {
            Value = value;
        }

        public String getValueId() {
            return ValueId;
        }

        public void setValueId(String valueId) {
            ValueId = valueId;
        }

        public String getVariable() {
            return Variable;
        }

        public void setVariable(String variable) {
            Variable = variable;
        }

        public String getVariableId() {
            return VariableId;
        }

        public void setVariableId(String variableId) {
            VariableId = variableId;
        }

        @Override
        public String toString() {
            return "Results{" +
                    "Value='" + Value + '\'' +
                    ", ValueId='" + ValueId + '\'' +
                    ", Variable='" + Variable + '\'' +
                    ", VariableId='" + VariableId + '\'' +
                    '}';
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(getCount(), model.getCount()) && Objects.equals(getMessage(), model.getMessage()) && Objects.equals(getSearchCriteria(), model.getSearchCriteria()) && Objects.equals(getResults(), model.getResults());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(getCount(), getMessage(), getSearchCriteria(), getResults());
    }
}
