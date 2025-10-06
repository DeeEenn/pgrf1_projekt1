# PGRF Projekt 1

> **Interaktivní kreslení úseček a polygonů s DDA rasterizačním algoritmem**
> 
## Hlavní funkce

### **Interaktivní kreslení**
- **Pružná čára**: Táhněte myší pro náhled před dokončením
- **Polygony**: Vytvářejte uzavřené n-úhelníky postupným přidáváním vrcholů
- **Více objektů**: Kreslení několika čar a polygonů současně

### **Vizuální efekty**
- **Barevná interpolace**: Plynulý přechod barev mezi koncovými body (klávesa `I`)
- **Tlusté čáry**: Přepínání mezi tenkou (1px) a tlustou (3px) čárou (klávesa `T`)
- **Shift alignment**: Prepnutim do Shift modu zarovnáte čáru horizontálně/vertikálně

### **Klávesové zkratky**
| Klávesa | Akce |
|---------|------|
| `C` | Vymazání celého plátna |
| `T` | Přepínání tloušťky čáry (1px ↔ 3px) |
| `I` | Zapnutí/vypnutí barevné interpolace |
| `P` | Přepínání mezi režimem čar a polygonů |
| `Shift` | Zarovnání na horizontální/vertikální směr |

## Architektura projektu

```
src/main/java/projekt1/
├── Main.java                    # Vstupní bod aplikace
├── controller/
│   └── Controller2D.java        # Hlavní logika a event handling
├── model/
│   ├── Point.java              # 2D bod s barvou
│   ├── Line.java               # Úsečka s interpolací barev
│   └── Polygon.java            # N-úhelník s kolekci vrcholů
├── rasterizer/
│   ├── Raster.java             # Interface pro pixel operace
│   ├── RasterBufferedImage.java # Implementace nad BufferedImage
│   ├── LineRasterizer.java     # Abstraktní třída pro vykreslování
│   └── FilledLineRasterizer.java # DDA algoritmus
└── view/
    ├── Frame.java              # Hlavní okno s menu
    └── Panel.java              # Kreslicí plátno
```

## Technické detaily

### DDA Algoritmus (Digital Differential Analyzer)
Projekt používá **DDA algoritmus** pro rasterizaci úseček:

**Výhody:**
- ✅ Jednoduchá implementace
- ✅ Funguje pro všechny sklony čar
- ✅ Přesné výsledky

**Nevýhody:**
- ❌ Pomalejší než Bresenham (floating-point aritmetika)
- ❌ Možné zaokrouhlovací chyby u velmi dlouhých čar

### Barevná interpolace
```java
// Lineární interpolace mezi dvěma barvami
Color interpolated = (1-t) * startColor + t * endColor
```
Kde `t ∈ [0,1]` reprezentuje pozici na úsečce.

## Jak na to?

### Kreslení úseček
1. **Klikněte** levým tlačítkem myši pro začátek úsečky
2. **Táhněte** myší - uvidíte náhled pružné čáry
3. **Pusťte** tlačítko pro dokončení

### Vytváření polygonů  
1. Stiskněte **P** pro přepnutí do polygon režimu
2. **Klikejte** postupně na vrcholy polygonu
3. Polygon se automaticky uzavře spojením posledního vrcholu s prvním

### Barevné efekty
- Stiskněte **I** pro zapnutí barevné interpolace
- Nové čáry budou mít plynulý přechod od červené k modré

