package com.example.sqliteapplication;

public enum  Categorie {

        PERSO ("Personnel"),
        TRAVAIL ("Professionnel"),
        RIGOLO ("Rigolo");

        private String name = "";

        Categorie(String name){
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String toString(){
            return name;
        }

}
