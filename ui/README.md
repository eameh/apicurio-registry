# Apicurio Registry web UI verification

Direct Playwright screenshots and an unedited browser recording from 13 October 2026. Frontend 3.4.0-Dev and backend 3.4.0-SNAPSHOT use source [c16b26797](https://github.com/Apicurio/apicurio-registry/tree/c16b2679763d23b82656f01a125e4dcc36e269c2). The disposable Registry uses H2, with authentication disabled. No API mocks, generated screen summaries, or replaced page content were used.

The rule workflow creates `ui-rule-inheritance/prices` through the UI, sets group compatibility to Backward and artifact validity to Full, then submits an incompatible version. The UI displays the actual compatibility error. A compatible version succeeds; after setting artifact compatibility to None, the previously rejected version also succeeds. Group Backward remains configured throughout.

The other two screenshots show UI navigation to the content of `my-group/share-price/1.0.0` and `client-maven-live/FullNameRecordLive/1`, freshly registered with the corrected API and Maven commands. These are UI read-back checks; the shell/API failures remain documented in the terminal evidence. HA, telemetry and interactive CLI behavior were not retested through the web UI.

[Browser recording](ui-session.webm) (5m32s; product interaction begins around 3m16s). [Observed API responses](network-responses.jsonl) retain response bodies and timestamps; binary compressed request bodies are omitted. The session trace did not finalize during browser shutdown, so it is not included. The screenshots and recording were saved directly from Chromium. Schemas were independently retrieved and compared with the test fixtures.

Related issues: [Docker #10539](https://github.com/Apicurio/apicurio-registry/issues/10539), [Maven #10540](https://github.com/Apicurio/apicurio-registry/issues/10540), [rules #10541](https://github.com/Apicurio/apicurio-registry/issues/10541).
