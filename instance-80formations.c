#include <stdio.h>
                  
#define NBR_INTERFACES        96
#define NBR_APPRENANTS        80
#define NBR_FORMATIONS        80
#define NBR_CENTRES_FORMATION 3
#define NBR_SPECIALITES       3
#define NBR_NODES 	      NBR_CENTRES_FORMATION+NBR_INTERFACES+NBR_APPRENANTS
                  
/* code des compétence en langage des signes et en codage LPC */
#define COMPETENCE_SIGNES     0
#define COMPETENCE_CODAGE     1
                  
/* competences des interfaces en SIGNES et CODAGE*/
int competences_interfaces[NBR_INTERFACES][2]={
    {1,0}, /* compétence en langages des SIGNES mais pas en CODAGE LPC */
    {0,1}, /* pas de compétence en langages des SIGNES mais compétence en CODAGE LPC */
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {0,1},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {1,0},
    {0,1},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {1,0},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {1,0},
    {1,0},
    {1,0},
    {0,1},
    {0,1},
    {1,0}
};
                  
/* spécialités des interfaces */
#define SPECIALITE_SANS       -1 /* Enseigné dans le centre le plus proche */
#define SPECIALITE_MENUISERIE  0 
#define SPECIALITE_ELECTRICITE 1
#define SPECIALITE_MECANIQUE   2
                  
/* specialite des interfaces */
int specialite_interfaces[NBR_INTERFACES][NBR_SPECIALITES]={
    {0,0,0},
    {0,0,1},
    {0,0,0},
    {1,0,0},
    {0,1,1},
    {0,0,0},
    {1,0,1},
    {1,0,1},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,1},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,1,0},
    {0,0,0},
    {0,1,1},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,1,1},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,0},
    {0,0,0},
    {0,1,0},
    {0,1,1},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {1,0,1},
    {0,0,0},
    {0,1,0},
    {1,0,0},
    {0,0,1},
    {0,0,0},
    {1,0,0},
    {1,0,0},
    {0,1,0},
    {0,0,1},
    {0,1,0},
    {0,0,0},
    {0,0,0},
    {0,0,1},
    {0,0,0},
    {0,0,0},
    {1,1,0},
    {0,0,1},
    {0,0,1},
    {0,0,0},
    {0,1,1},
    {0,0,0},
    {0,1,0},
    {0,0,0},
    {0,1,0},
    {0,0,0},
    {0,0,1},
    {1,1,0},
    {0,0,0},
    {0,0,0},
    {1,1,1},
    {0,0,0},
    {0,0,1},
    {1,0,0},
    {1,1,0},
    {1,0,0},
    {0,1,0},
    {0,0,0},
    {1,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,1},
    {1,0,1},
    {0,0,0},
    {0,0,0},
    {0,0,0},
    {0,0,0}
};
                  
/* coordonnées des centres de formation, des interfaces et des apprenants */
float coord[NBR_NODES][2]={
                  
    /* Les interfaces se rendent du centre SESSAD à l'école de formation */
    {82,91}, /* centre 0 */
                  
    /* Centres de formation */
    {159,160}, /* ecole formation SPECIALITE_MENUISERIE */
    {11,1}, /* ecole formation SPECIALITE_ELECTRICITE */
    {192,142}, /* ecole formation SPECIALITE_MECANIQUE */
                  
    /* Apprenants */
    {4,78}, /* apprenant 0 */
    {155,144}, /* apprenant 1 */
    {150,61}, /* apprenant 2 */
    {88,5}, /* apprenant 3 */
    {134,198}, /* apprenant 4 */
    {167,158}, /* apprenant 5 */
    {195,76}, /* apprenant 6 */
    {1,158}, /* apprenant 7 */
    {50,4}, /* apprenant 8 */
    {141,156}, /* apprenant 9 */
    {128,96}, /* apprenant 10 */
    {102,164}, /* apprenant 11 */
    {123,116}, /* apprenant 12 */
    {91,126}, /* apprenant 13 */
    {158,146}, /* apprenant 14 */
    {72,59}, /* apprenant 15 */
    {13,121}, /* apprenant 16 */
    {36,53}, /* apprenant 17 */
    {80,27}, /* apprenant 18 */
    {81,20}, /* apprenant 19 */
    {197,177}, /* apprenant 20 */
    {60,22}, /* apprenant 21 */
    {198,161}, /* apprenant 22 */
    {194,85}, /* apprenant 23 */
    {44,73}, /* apprenant 24 */
    {133,6}, /* apprenant 25 */
    {194,22}, /* apprenant 26 */
    {77,110}, /* apprenant 27 */
    {8,150}, /* apprenant 28 */
    {179,153}, /* apprenant 29 */
    {98,4}, /* apprenant 30 */
    {103,11}, /* apprenant 31 */
    {155,128}, /* apprenant 32 */
    {110,51}, /* apprenant 33 */
    {44,185}, /* apprenant 34 */
    {21,164}, /* apprenant 35 */
    {98,142}, /* apprenant 36 */
    {28,56}, /* apprenant 37 */
    {66,122}, /* apprenant 38 */
    {190,124}, /* apprenant 39 */
    {64,141}, /* apprenant 40 */
    {7,81}, /* apprenant 41 */
    {115,53}, /* apprenant 42 */
    {32,19}, /* apprenant 43 */
    {44,44}, /* apprenant 44 */
    {147,29}, /* apprenant 45 */
    {63,123}, /* apprenant 46 */
    {22,93}, /* apprenant 47 */
    {70,110}, /* apprenant 48 */
    {37,150}, /* apprenant 49 */
    {160,121}, /* apprenant 50 */
    {164,61}, /* apprenant 51 */
    {68,23}, /* apprenant 52 */
    {190,29}, /* apprenant 53 */
    {20,47}, /* apprenant 54 */
    {156,17}, /* apprenant 55 */
    {23,32}, /* apprenant 56 */
    {113,176}, /* apprenant 57 */
    {98,84}, /* apprenant 58 */
    {115,104}, /* apprenant 59 */
    {10,164}, /* apprenant 60 */
    {57,17}, /* apprenant 61 */
    {41,7}, /* apprenant 62 */
    {153,150}, /* apprenant 63 */
    {124,145}, /* apprenant 64 */
    {11,164}, /* apprenant 65 */
    {155,95}, /* apprenant 66 */
    {176,195}, /* apprenant 67 */
    {168,144}, /* apprenant 68 */
    {102,67}, /* apprenant 69 */
    {99,90}, /* apprenant 70 */
    {99,16}, /* apprenant 71 */
    {27,73}, /* apprenant 72 */
    {46,20}, /* apprenant 73 */
    {131,148}, /* apprenant 74 */
    {142,56}, /* apprenant 75 */
    {92,64}, /* apprenant 76 */
    {116,127}, /* apprenant 77 */
    {114,43}, /* apprenant 78 */
    {119,198}/* apprenant 79 */
};
                  
#define NBR_FORMATION          80
                  
#define LUNDI                  1
#define MARDI                  2
#define MERCREDI               3
#define JEUDI                  4
#define VENDREDI               5
#define SAMEDI                 6
                  
/* formation : id formation, specialite ou centre de formation, competence, horaire debut formation, horaire fin formation */
int formation[NBR_FORMATION][6]={
   {0,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MERCREDI,16,18},
   {1,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,VENDREDI,10,12},
   {2,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,SAMEDI,10,12},
   {3,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,9,12},
   {4,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,13,15},
   {5,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,9,12},
   {6,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,SAMEDI,10,12},
   {7,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,LUNDI,13,18},
   {8,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,MERCREDI,8,12},
   {9,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,9,12},
   {10,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,LUNDI,14,17},
   {11,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,MARDI,8,11},
   {12,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,10,12},
   {13,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MERCREDI,16,18},
   {14,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,8,11},
   {15,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,MERCREDI,9,12},
   {16,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MARDI,10,12},
   {17,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,VENDREDI,8,10},
   {18,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MERCREDI,10,12},
   {19,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,SAMEDI,8,11},
   {20,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,SAMEDI,13,16},
   {21,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,JEUDI,8,10},
   {22,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,LUNDI,8,12},
   {23,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,LUNDI,14,16},
   {24,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,15,18},
   {25,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MARDI,9,11},
   {26,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,10,12},
   {27,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,8,10},
   {28,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,8,11},
   {29,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,LUNDI,8,11},
   {30,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,JEUDI,8,10},
   {31,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,8,12},
   {32,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,9,11},
   {33,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,14,17},
   {34,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,VENDREDI,15,19},
   {35,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,LUNDI,9,11},
   {36,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,16,18},
   {37,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,JEUDI,8,11},
   {38,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,VENDREDI,10,12},
   {39,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,9,11},
   {40,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,MARDI,8,11},
   {41,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,8,12},
   {42,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,VENDREDI,8,11},
   {43,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,10,12},
   {44,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,VENDREDI,9,12},
   {45,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,15,19},
   {46,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,15,19},
   {47,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,15,17},
   {48,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,SAMEDI,9,12},
   {49,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MARDI,15,18},
   {50,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,MARDI,15,19},
   {51,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MARDI,10,12},
   {52,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,JEUDI,14,16},
   {53,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,JEUDI,10,12},
   {54,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,LUNDI,16,19},
   {55,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,MARDI,10,12},
   {56,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,9,11},
   {57,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MERCREDI,14,16},
   {58,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,SAMEDI,13,19},
   {59,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,LUNDI,15,17},
   {60,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MARDI,8,12},
   {61,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,SAMEDI,16,18},
   {62,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,MERCREDI,13,16},
   {63,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,JEUDI,15,17},
   {64,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,SAMEDI,15,17},
   {65,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MARDI,9,12},
   {66,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,VENDREDI,15,17},
   {67,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,VENDREDI,13,15},
   {68,SPECIALITE_MENUISERIE,COMPETENCE_SIGNES,LUNDI,13,19},
   {69,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,MERCREDI,14,16},
   {70,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,JEUDI,10,12},
   {71,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,9,12},
   {72,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,MERCREDI,10,12},
   {73,SPECIALITE_MECANIQUE,COMPETENCE_SIGNES,SAMEDI,8,12},
   {74,SPECIALITE_ELECTRICITE,COMPETENCE_CODAGE,SAMEDI,15,17},
   {75,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,JEUDI,10,12},
   {76,SPECIALITE_ELECTRICITE,COMPETENCE_SIGNES,MERCREDI,8,10},
   {77,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,LUNDI,16,18},
   {78,SPECIALITE_MECANIQUE,COMPETENCE_CODAGE,MERCREDI,15,19},
   {79,SPECIALITE_MENUISERIE,COMPETENCE_CODAGE,VENDREDI,10,12}
};
                  
int main() {
                  
    printf("NBR_INTERFACES=%d\n",NBR_INTERFACES) ;
    printf("NBR_APPRENANTS=%d\n",NBR_APPRENANTS) ;
    printf("NBR_FORMATIONS=%d\n",NBR_FORMATIONS) ;
    printf("NBR_NODES=%d\n",NBR_NODES) ;
                  
    return 0 ;
}
                  
