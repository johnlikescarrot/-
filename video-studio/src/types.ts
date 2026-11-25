export interface ChapterSpec {
  id: string;
  index: number;
  tag: string;
  title: string;
  subtitle: string;
  narrationText: string;
  /** Default duration in seconds before real TTS wav durations are measured */
  defaultDurationSec: number;
  /** Key quantitative takeaways shown cleanly in HUD/subtitles */
  captions: Array<{
    startRatio: number;
    endRatio: number;
    text: string;
  }>;
}

export interface ChapterTiming extends ChapterSpec {
  durationSec: number;
  startSec: number;
  endSec: number;
  startFrame: number;
  endFrame: number;
  audioPath?: string;
  audioDurationSec?: number;
}

export interface NativeLibRecord {
  name: string;
  bytes: number;
  sha256: string;
  overall_entropy: number;
  text_bytes: number;
  text_entropy: number;
  rodata_bytes: number;
  rodata_entropy: number;
  section_count: number;
  needed_libs: string[];
  init_count: number;
  exported_functions: number;
  rea_dynamic_exports: number;
  jni_exports: string[];
  hardening: {
    nx: string | null;
    relro: string | null;
    stack_protector: string | null;
    fortify: string | null;
    stripped: string | null;
    rpath: string | null;
    runpath: string | null;
  };
  dominant_isa: string | null;
  svc_syscalls_sampled: number;
  keywords: Record<string, number>;
  build_id: string | null;
}

export interface ResearchDataset {
  target: {
    outer_zip: { filename: string; bytes: number; sha256: string };
    xapk: {
      filename: string;
      bytes: number;
      sha256: string;
      member_count: number;
      members: Array<{ filename: string; file_size: number; sha256: string }>;
      manifest_json: {
        package_name: string;
        name: string;
        version_code: string;
        version_name: string;
        min_sdk: string;
        target_sdk: string;
        split_configs: string[];
      };
    };
    apks: Record<
      string,
      {
        bytes: number;
        sha256: string;
        entry_count: number;
        total_uncompressed: number;
        rea_strongest_scheme: string;
        signer_subject: string | null;
        signer_sha256: string | null;
        source_stamp_org: string | null;
        rea_signature_posture: {
          v1_only: boolean;
          schemes_agree_on_signer: boolean;
          source_stamp_present: boolean;
          self_consistent: boolean;
        };
      }
    >;
  };
  manifest: {
    min_sdk: string;
    target_sdk: string;
    compile_sdk: string;
    permissions_count: number;
    permission_categories: Record<string, number>;
    component_summary: Record<
      string,
      { total: number; exported: number; exported_without_permission: number }
    >;
    total_components: number;
    multi_processes: string[];
    queries_packages: string[];
    deep_link_schemes: string[];
    deep_link_hosts: string[];
    meta_data_count: number;
  };
  resources: {
    arsc: Record<
      string,
      {
        bytes: number;
        locales: string[];
        total_resource_keys: number;
        resource_types_counts: Record<string, number>;
      }
    >;
    raw_count: number;
    raw_total_bytes: number;
  };
  dex_and_bytecode: {
    primary_dex_count: number;
    primary_totals: {
      string_ids: number;
      type_ids: number;
      proto_ids: number;
      field_ids: number;
      method_ids: number;
      class_defs: number;
      defined_methods: number;
      methods_with_code: number;
      total_dalvik_instructions: number;
      total_dex_bytes: number;
      short_class_names_le2: number;
      obfuscated_root_pkg_classes: number;
      short_method_names_le2: number;
    };
    obfuscation_rates: {
      short_class_name_pct: number;
      obfuscated_root_pkg_pct: number;
      short_method_name_pct: number;
    };
    framework_and_security_classes: Record<string, number>;
    opcode_families: Record<string, number>;
    top_opcodes: Array<[string, number]>;
    audience_network_dynamic_dex: {
      bytes: number;
      counts: { class_defs: number; defined_methods: number; total_dalvik_instructions: number };
    };
    native_methods_in_dex: number;
    native_method_classes: number;
    sbom_counts: {
      meta_inf_versions: number;
      root_properties: number;
      meta_inf_services: number;
    };
  };
  hybrid_runtimes_and_ai: {
    react_native: {
      main_jsbundle_bytes: number;
      metro_module_define_count: number;
    };
    griver_amrs: Record<
      string,
      { tar_member_count: number; manifest_xml: string }
    >;
    rea_js_bridges_stats: {
      parsed_javascript_files: number;
      visited_ast_nodes: number;
      text_bytes_read: number;
    };
    ncnn_fastestdet: {
      layer_and_blob_counts: string;
      layer_type_histogram: Record<string, number>;
    };
    encrypted_or_high_entropy_assets: Array<{
      path: string;
      bytes: number;
      entropy: number;
    }>;
    assets_total_count: number;
    assets_total_bytes: number;
  };
  native_elf_summary: {
    count: number;
    total_bytes: number;
    libraries: NativeLibRecord[];
    liblytcuhe_vs_librcmefqonnx_diff_bytes: number;
  };
}
