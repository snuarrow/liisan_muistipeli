liisan_muistipeli

Muistipeli fysiikkamoottorilla.
Dokumentaatiot omassa kansiossaan.

PIT reporttia en saanut toimimaan, on käyty pajassa, kuuklattu, luotu Maven projekti uusiksi, kuuklattu lisää, kokeiltu erilaisia vaihtoehtoja ja ei toimi. luovutan sen osalta toistaiseksi.

tässä vielä output custom/pit:




------------------------------------------------------------------------
Building liisan_muistipeli 1.0
------------------------------------------------------------------------

--- pitest-maven:1.1.4:mutationCoverage (default-cli) @ liisan_muistipeli ---
Found plugin : Default csv report plugin
Found plugin : Default xml report plugin
Found plugin : Default html report plugin
Found plugin : Default limit mutations plugin
Found shared classpath plugin : Default mutation engine
01:47:26 PIT >> INFO : Mutating from /home/hexvaara/Documents/Courses/javalabra/liisan_muistipeli/target/classes
Defaulting to group id (javalabra.liisan_muistipeli*)
01:47:26 PIT >> INFO : Verbose logging is disabled. If you encounter an problem please enable it before reporting an issue.
01:47:26 PIT >> INFO : SLAVE : Picked up JAVA_TOOL_OPTIONS: -javaagent:/usr/share/java/jayatanaag.jar 

01:47:26 PIT >> INFO : Sending 3 test classes to slave
01:47:26 PIT >> INFO : Sent tests to slave
01:47:26 PIT >> INFO : SLAVE : 01:47:26 PIT >> INFO : Found  17 tests

01:47:26 PIT >> INFO : SLAVE : 01:47:26 PIT >> INFO : Dependency analysis reduced number of potential tests by 0

01:47:26 PIT >> INFO : SLAVE : 01:47:26 PIT >> INFO : 17 tests received

01:47:26 PIT >> INFO : SLAVE : 01:47:26 PIT >> WARNING : More threads at end of test (7) testConstructor_id_correct_values1(javalabra.liisan_muistipeli.logic.CardTest) than start. (4)

/01:47:27 PIT >> INFO : Calculated coverage in 0 seconds.
01:47:27 PIT >> INFO : Created  6 mutation test units
-01:47:27 PIT >> INFO : Completed in 1 seconds
================================================================================
- Timings
================================================================================
> scan classpath : < 1 second
> coverage and dependency analysis : < 1 second
> build mutation tests : < 1 second
> run mutation analysis : < 1 second
--------------------------------------------------------------------------------
> Total  : 1 seconds
--------------------------------------------------------------------------------
================================================================================
- Statistics
================================================================================
>> Generated 152 mutations Killed 0 (0%)
>> Ran 0 tests (0 tests per mutation)
================================================================================
- Mutators
================================================================================
> org.pitest.mutationtest.engine.gregor.mutators.MathMutator
>> Generated 55 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 55 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.ConditionalsBoundaryMutator
>> Generated 14 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 14 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.ReturnValsMutator
>> Generated 35 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 35 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.VoidMethodCallMutator
>> Generated 13 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 13 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.IncrementsMutator
>> Generated 8 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 8 
--------------------------------------------------------------------------------
> org.pitest.mutationtest.engine.gregor.mutators.NegateConditionalsMutator
>> Generated 27 Killed 0 (0%)
> KILLED 0 SURVIVED 0 TIMED_OUT 0 NON_VIABLE 0 
> MEMORY_ERROR 0 NOT_STARTED 0 STARTED 0 RUN_ERROR 0 
> NO_COVERAGE 27 
--------------------------------------------------------------------------------
------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time: 2.895s
Finished at: Sat Aug 08 01:47:27 EEST 2015
Final Memory: 8M/205M
------------------------------------------------------------------------
