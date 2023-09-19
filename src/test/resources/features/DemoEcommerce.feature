Feature: Demo Ecommerce

  Scenario Outline: Test 1: Petición a las 10:00 del día 14 de junio del producto 35455   para la brand 1 (ZARA)
    When dado la fecha y hora "<date>", código de producto "<productId>" y la cadena del grupo "<brandId>"
    Then el código HTTP de respuesta es "<status>"

    Examples:
      | date                | productId | brandId | status |
      | 2020-06-14T10:00:00 | 35455     | 1       | 200    |

  Scenario Outline: Test 2: petición a las 16:00 del día 14 de junio del producto 35455   para la brand 1 (ZARA)
    When dado la fecha y hora "<date>", código de producto "<productId>" y la cadena del grupo "<brandId>"
    Then el código HTTP de respuesta es "<status>"

    Examples:
      | date                | productId | brandId | status |
      | 2020-06-14T16:00:00 | 35455     | 1       | 200    |

  Scenario Outline: Test 3: petición a las 21:00 del día 14 de junio del producto 35455   para la brand 1 (ZARA)
    When dado la fecha y hora "<date>", código de producto "<productId>" y la cadena del grupo "<brandId>"
    Then el código HTTP de respuesta es "<status>"

    Examples:
      | date                | productId | brandId | status |
      | 2020-06-14T21:00:00 | 35455     | 1       | 200    |

  Scenario Outline: Test 4: petición a las 10:00 del día 15 de junio del producto 35455   para la brand 1 (ZARA)
    When dado la fecha y hora "<date>", código de producto "<productId>" y la cadena del grupo "<brandId>"
    Then el código HTTP de respuesta es "<status>"

    Examples:
      | date                | productId | brandId | status |
      | 2020-06-15T10:00:00 | 35455     | 1       | 200    |

  Scenario Outline: Test 5: petición a las 21:00 del día 16 de junio del producto 35455   para la brand 1 (ZARA)
    When dado la fecha y hora "<date>", código de producto "<productId>" y la cadena del grupo "<brandId>"
    Then el código HTTP de respuesta es "<status>"

    Examples:
      | date                | productId | brandId | status |
      | 2020-06-14T21:00:00 | 35455     | 1       | 200    |
