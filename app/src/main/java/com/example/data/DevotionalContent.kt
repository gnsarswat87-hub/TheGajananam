package com.example.data

import com.example.model.DevotionalCategory
import com.example.model.DevotionalItem
import com.example.model.JapaMantra
import com.example.model.WordMeaning

object DevotionalContent {

    val GAJANANAM_SHLOKA_SANSKRIT = """
        गजाननं भूतगणादिसेवितं
        कपित्थजम्बूफलसारभक्षितम् ।
        उमासुतं शोकविनाशकारणं
        नमामि विघ्नेश्वरपादपङ्कजम् ॥
    """.trimIndent()

    val GAJANANAM_TRANSLITERATION = """
        Gajānanaṁ Bhūta-Gaṇādi-Sevitam
        Kapittha-Jambū-Phala-Sāra-Bhakṣitam |
        Umā-Sutaṁ Śoka-Vināśa-Kāraṇaṁ
        Namāmi Vighneśvara-Pāda-Paṅkajam ||
    """.trimIndent()

    val GAJANANAM_MEANING = """
        I bow to the lotus feet of Lord Vighneshwara (Ganesha), the elephant-faced deity, who is devotedly served by celestial attendants and nature spirits, who relishes the sacred essence of wood-apple (Kapittha) and rose-apple (Jambu) fruits, who is the beloved divine son of Mother Uma (Parvati), and who is the ultimate destroyer of all grief, suffering, and sorrow.
    """.trimIndent()

    val GAJANANAM_WORDS = listOf(
        WordMeaning(
            wordSanskrit = "गजाननम् (Gajānanam)",
            wordEnglish = "Elephant-Faced Lord",
            meaning = "Gaja (Elephant) + Anana (Face)",
            explanation = "Signifies immense wisdom, majesty, keen listening through large ears, and the all-encompassing cosmic consciousness."
        ),
        WordMeaning(
            wordSanskrit = "भूतगणादिसेवितम् (Bhūta-Gaṇādi-Sevitam)",
            wordEnglish = "Served by Nature's Beings & Attendants",
            meaning = "Bhuta (Living beings & elemental spirits) + Gana (Celestial attendants) + Sevitam (Served)",
            explanation = "Lord Ganesha is the benevolent ruler of Lord Shiva's Ganas, worshipped by all orders of cosmic creation without distinction."
        ),
        WordMeaning(
            wordSanskrit = "कपित्थजम्बूफलसारभक्षितम् (Kapittha-Jambū-Phala-Sāra-Bhakṣitam)",
            wordEnglish = "Relishing Essence of Sacred Fruits",
            meaning = "Kapittha (Wood apple) + Jambu (Rose apple / Jamun) + Phala (Fruit) + Sara (Core essence) + Bhakshitam (Consumed)",
            explanation = "Symbolizes extracting the innermost spiritual nectar and pure essence from the fruits of worldly actions and life."
        ),
        WordMeaning(
            wordSanskrit = "उमासुतम् (Umā-Sutam)",
            wordEnglish = "Son of Mother Uma (Parvati)",
            meaning = "Beloved child of Goddess Parvati (Shakti)",
            explanation = "Emphasizes His direct embodiment of maternal love, divine grace, strength, and primal creative cosmic energy."
        ),
        WordMeaning(
            wordSanskrit = "शोकविनाशकारणम् (Śoka-Vināśa-Kāraṇam)",
            wordEnglish = "Destroyer of All Sorrow",
            meaning = "Shoka (Grief & affliction) + Vinasha (Total destruction) + Karanam (The cause)",
            explanation = "The radiant remover of existential fears, anxiety, grief, and impediments on the spiritual and material path."
        ),
        WordMeaning(
            wordSanskrit = "नमामि विघ्नेश्वरपादपङ्कजम् (Namāmi Vighneśvara-Pāda-Paṅkajam)",
            wordEnglish = "I Bow to His Lotus Feet",
            meaning = "Namami (I offer salutations) + Vighneshwara (Lord of obstacles) + Pada-Pankajam (Lotus feet)",
            explanation = "Total spiritual surrender at the lotus feet that rise pristine above worldly waters, blessing the seeker with success."
        )
    )

    val JAPA_MANTRAS = listOf(
        JapaMantra(
            id = "m1",
            name = "Ganesha Moola Mantra",
            devanagari = "ॐ गं गणपतये नमः ॥",
            transliteration = "Om Gaṁ Gaṇapataye Namaḥ",
            meaning = "Salutations to Lord Ganapati, the master of all groups and wisdom."
        ),
        JapaMantra(
            id = "m2",
            name = "Gajananam Stuti",
            devanagari = "गजाननं भूतगणादिसेवितं नमामि विघ्नेश्वरपादपङ्कजम् ॥",
            transliteration = "Gajānanaṁ Bhūta-Gaṇādi-Sevitaṁ Namāmi Vighneśvara-Pāda-Paṅkajam",
            meaning = "Surrendering to the elephant-faced Lord who removes all sorrow."
        ),
        JapaMantra(
            id = "m3",
            name = "Ganesha Gayatri Mantra",
            devanagari = "ॐ एकदन्ताय विद्महे वक्रतुण्डाय धीमहि तन्नो दन्तिः प्रचोदयात् ॥",
            transliteration = "Om Ekadantāya Vidmahe Vakratuṇḍāya Dhīmahi Tanno Dantiḥ Pracodayāt",
            meaning = "May the single-tusked, curved-trunk deity illumine our intellect."
        ),
        JapaMantra(
            id = "m4",
            name = "Vakratunda Shloka",
            devanagari = "वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ । निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा ॥",
            transliteration = "Vakratuṇḍa Mahākāya Sūryakoṭi Samaprabha | Nirvighnaṁ Kuru Me Deva Sarva-Kāryeṣu Sarvadā ||",
            meaning = "O Lord of curved trunk and cosmic form, make all my undertakings free from obstacles."
        )
    )

    val STOTRAS_AND_PRAYERS = listOf(
        DevotionalItem(
            id = "sankat_nashan",
            title = "Sankata Nashanam Ganapati Stotram",
            subtitle = "From Narada Purana • Twelve Sacred Names",
            devanagari = """
                प्रणम्य शिरसा देवं गौरीपुत्रं विनायकम् ।
                भक्तावासं स्मरेन्नित्यमायुष्कामार्थसिद्धये ॥ १ ॥
                प्रथमं वक्रतुण्डं च एकदन्तं द्वितीयकम् ।
                तृतीयं कृष्णपिङ्गाक्षं गजवक्त्रं चतुर्थकम् ॥ २ ॥
                लम्बोदरं पञ्चमं च षष्ठं विकटमेव च ।
                सप्तमं विघ्नराजेन्द्रं धूम्रवर्णं तथाष्टमम् ॥ ३ ॥
                नवमं भालचन्द्रं च दशमं तु विनायकम् ।
                एकादशं गणपतिं द्वादशं तु गजाननम् ॥ ४ ॥
                द्वादशैतानि नामानि त्रिसंध्यं यः पठेन्नरः ।
                न च विघ्नभयं तस्य सर्वसिद्धिकरं परम् ॥ ५ ॥
            """.trimIndent(),
            transliteration = """
                Praṇamya śirasā devaṁ Gaurī-putraṁ Vināyakam |
                Bhaktāvāsaṁ smaren-nityam āyuṣ-kāmārtha-siddhaye || 1 ||
                Prathamaṁ Vakratuṇḍaṁ ca Ekadantaṁ dvitīyakam |
                Tṛtīyaṁ Kṛṣṇa-piṅgākṣaṁ Gajavaktraṁ caturthakam || 2 ||
                Lambodaraṁ pañcamaṁ ca ṣaṣṭhaṁ Vikaṭam-eva ca |
                Saptamaṁ Vighna-rājendraṁ Dhūmra-varṇaṁ tathāṣṭamam || 3 ||
                Navamaṁ Bhālacandraṁ ca Daśamaṁ tu Vināyakam |
                Ekādaśaṁ Gaṇapatiṁ Dvādaśaṁ tu Gajānanam || 4 ||
            """.trimIndent(),
            translation = "Bowing the head to the divine Vinayaka, son of Gauri. Reciting these twelve sacred names at dawn, noon, and dusk frees one from all fear of obstacles and grants supreme fulfillment of noble desires.",
            significance = "Traditional daily armor recited before beginning studies, journeys, or new endeavors.",
            category = DevotionalCategory.STOTRAM
        ),
        DevotionalItem(
            id = "vakratunda",
            title = "Vakratunda Mahakaya",
            subtitle = "Classic Mangalacharan Invocation",
            devanagari = """
                वक्रतुण्ड महाकाय सूर्यकोटि समप्रभ ।
                निर्विघ्नं कुरु मे देव सर्वकार्येषु सर्वदा ॥
            """.trimIndent(),
            transliteration = """
                Vakratuṇḍa Mahākāya Sūryakoṭi Samaprabha |
                Nirvighnaṁ Kuru Me Deva Sarva-Kāryeṣu Sarvadā ||
            """.trimIndent(),
            translation = "O Lord with the curved trunk and immense cosmic body, shining with the effulgence of ten million suns; please make all my works and noble deeds free of obstacles at all times.",
            significance = "Universal opening prayer chanted before any puja, speech, work, or milestone.",
            category = DevotionalCategory.SHLOKA
        ),
        DevotionalItem(
            id = "atharvashirsha",
            title = "Ganapati Atharvashirsha (Core Shloka)",
            subtitle = "From Atharva Veda Upanishad",
            devanagari = """
                नमस्ते गणपतये । त्वमेव प्रत्यक्षं तत्त्वमसि ।
                त्वमेव केवलं कर्ताऽसि । त्वमेव केवलं धर्ताऽसि ।
                त्वमेव केवलं हर्ताऽसि । त्वमेव सर्वं खल्विदं ब्रह्मासि ।
                त्वं साक्षादात्माऽसि नित्यम् ॥
            """.trimIndent(),
            transliteration = """
                Namaste Gaṇapataye | Tvameva pratyakṣaṁ tattvamasi |
                Tvameva kevalaṁ kartā'si | Tvameva kevalaṁ dhartā'si |
                Tvameva kevalaṁ hartā'si | Tvameva sarvaṁ khalvidaṁ Brahmāsi |
                Tvaṁ sākṣād-ātmā'si nityam ||
            """.trimIndent(),
            translation = "Salutations to You, O Ganapati. You alone are the visible Supreme Reality. You alone are the Creator, Sustainer, and Dissolver of creation. You alone are the infinite Brahman; You are the eternal Self residing within all.",
            significance = "The highest Vedic philosophical treatise revealing Lord Ganesha as the ultimate truth.",
            category = DevotionalCategory.MANTRA
        ),
        DevotionalItem(
            id = "aarti_jai_ganesh",
            title = "Shri Ganesh Aarti",
            subtitle = "Jai Ganesh Jai Ganesh Deva",
            devanagari = """
                जय गणेश, जय गणेश, जय गणेश देवा ।
                माता जाकी पार्वती, पिता महादेवा ॥
                एक दन्त दयावन्त, चार भुजाधारी ।
                माथे पर तिलक सोहे, मूसे की सवारी ॥
                पान चढ़े, फूल चढ़े और चढ़े मेवा ।
                लड्डुअन का भोग लगे, संत करें सेवा ॥
                अंधे को आँख देत, कोढ़िन को काया ।
                बाँझन को पुत्र देत, निर्धन को माया ॥
                'सूर' श्याम शरण आए, सफल कीजे सेवा ।
                जय गणेश, जय गणेश, जय गणेश देवा ॥
            """.trimIndent(),
            transliteration = """
                Jai Gaṇeś, Jai Gaṇeś, Jai Gaṇeś Devā |
                Mātā jākī Pārvatī, Pitā Mahādevā ||
                Eka danta dayāvanta, cāra bhujādhārī |
                Māthe para tilaka sohe, mūse kī savārī ||
                Pāna caṛhe, phūla caṛhe aura caṛhe mevā |
                Laḍḍuana kā bhoga lage, santa kareṁ sevā ||
                Jai Gaṇeś, Jai Gaṇeś, Jai Gaṇeś Devā ||
            """.trimIndent(),
            translation = "Glory to Lord Ganesha, whose mother is Parvati and father is Mahadeva! The compassionate single-tusked Lord with four arms, adorned with radiant sandalwood tilak, mounted on the celestial mooshaka. We offer betel leaves, fragrant blossoms, sacred modaks and laddoos in loving devotion.",
            significance = "Traditional evening and morning Aarti sung in households and temples across the world.",
            category = DevotionalCategory.AARTI
        ),
        DevotionalItem(
            id = "ganesha_pancharatnam",
            title = "Ganesha Pancharatnam (Verse 1)",
            subtitle = "Composed by Adi Shankaracharya",
            devanagari = """
                मुदाकरात्तमोदकं सदा विमुक्तिसाधकं
                कलाधरावतंसकं विलासिलोकरक्षकम् ।
                अनायकैकनायकं विनाशितेभदैत्यकं
                नताशुभाशुकल्पकं नमामि तं विनायकम् ॥
            """.trimIndent(),
            transliteration = """
                Mudā-karātta-modakaṁ sadā vimukti-sādhakaṁ
                Kalā-dharāvataṁsakaṁ vilāsi-loka-rakṣakam |
                Anāyakaika-nāyakaṁ vināśitebha-daityakaṁ
                Natāśubhāśu-kalpakaṁ namāmi taṁ Vināyakam ||
            """.trimIndent(),
            translation = "I bow to Vinayaka, who blissfully holds the sweet modak in His hand, who bestows liberation upon spiritual seekers, who wears the crescent moon as an ornament, and who shields the universe with divine love.",
            significance = "Exquisite lyrical stotram celebrating the joyful beauty and grace of Vinayaka.",
            category = DevotionalCategory.STOTRAM
        )
    )
}
