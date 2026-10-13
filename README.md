# Live documentation verification

The current evidence is under [live/](live/). These are direct screenshots of terminal sessions executing fresh Registry API, Maven, CLI, PostgreSQL pool and OpenTelemetry tests on 13 October 2026. The older rendered-documentation crops and generated result cards were superseded.

The captures are actual terminal pixels from a running ttyd session controlled with Playwright. Raw terminal output is retained with ANSI control sequences; Maven and CLI recordings are included. No output was replayed or rendered from written summaries.

API, rules, Maven and CLI captures use the 3.4.0-SNAPSHOT build at c16b2679763d23b82656f01a125e4dcc36e269c2. HA and telemetry use Registry3.3.3 / Quarkus3.33.3.1 with matching relevant source. HA pool output comes from the included read-only runtime probe; telemetry output comes from running Registry and Collector processes.
